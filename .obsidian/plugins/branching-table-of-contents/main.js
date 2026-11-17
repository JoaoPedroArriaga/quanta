/*
Branching Table of Contents — https://github.com/TonyPeonio/obsidian-branching-table-of-contents
This is a bundled build. The source is in the repository above.
*/

"use strict";
var __defProp = Object.defineProperty;
var __getOwnPropDesc = Object.getOwnPropertyDescriptor;
var __getOwnPropNames = Object.getOwnPropertyNames;
var __hasOwnProp = Object.prototype.hasOwnProperty;
var __export = (target, all) => {
  for (var name in all)
    __defProp(target, name, { get: all[name], enumerable: true });
};
var __copyProps = (to, from, except, desc) => {
  if (from && typeof from === "object" || typeof from === "function") {
    for (let key of __getOwnPropNames(from))
      if (!__hasOwnProp.call(to, key) && key !== except)
        __defProp(to, key, { get: () => from[key], enumerable: !(desc = __getOwnPropDesc(from, key)) || desc.enumerable });
  }
  return to;
};
var __toCommonJS = (mod) => __copyProps(__defProp({}, "__esModule", { value: true }), mod);

// src/main.ts
var main_exports = {};
__export(main_exports, {
  default: () => BtocPlugin
});
module.exports = __toCommonJS(main_exports);
var import_obsidian10 = require("obsidian");

// src/breadcrumbs.ts
var import_obsidian = require("obsidian");

// src/graph.ts
function emptyGraph() {
  return { nodes: /* @__PURE__ */ new Map(), parents: /* @__PURE__ */ new Map(), children: /* @__PURE__ */ new Map() };
}
function compareNodes(a, b) {
  const ao = a.order ?? Number.POSITIVE_INFINITY;
  const bo = b.order ?? Number.POSITIVE_INFINITY;
  if (ao !== bo) return ao < bo ? -1 : 1;
  const byName = a.name.localeCompare(b.name, void 0, { numeric: true, sensitivity: "base" });
  if (byName !== 0) return byName;
  return a.id < b.id ? -1 : a.id > b.id ? 1 : 0;
}
function compareIds(g) {
  return (a, b) => {
    const na = g.nodes.get(a);
    const nb = g.nodes.get(b);
    if (!na || !nb) return a < b ? -1 : a > b ? 1 : 0;
    return compareNodes(na, nb);
  };
}
function buildGraph(nodes, edges) {
  const g = emptyGraph();
  for (const n of nodes) if (!g.nodes.has(n.id)) g.nodes.set(n.id, n);
  const seen = /* @__PURE__ */ new Set();
  for (const e of edges) {
    if (e.parent === e.child) continue;
    if (!g.nodes.has(e.parent) || !g.nodes.has(e.child)) continue;
    const key = e.parent + "\0" + e.child;
    if (seen.has(key)) continue;
    seen.add(key);
    push(g.children, e.parent, e.child);
    push(g.parents, e.child, e.parent);
  }
  const cmp = compareIds(g);
  for (const list of g.children.values()) list.sort(cmp);
  for (const list of g.parents.values()) list.sort(cmp);
  return g;
}
function push(map, key, value) {
  const list = map.get(key);
  if (list) list.push(value);
  else map.set(key, [value]);
}
function parentsOf(g, id) {
  return g.parents.get(id) ?? [];
}
function childrenOf(g, id) {
  return g.children.get(id) ?? [];
}
function nameOf(g, id) {
  return g.nodes.get(id)?.name ?? id;
}
function neighborhood(g, focus, up, down) {
  const upDist = bfs(g, focus, up, parentsOf);
  const downDist = bfs(g, focus, down, childrenOf);
  const layer = /* @__PURE__ */ new Map();
  layer.set(focus, 0);
  for (const [id, d] of upDist) if (id !== focus) layer.set(id, -d);
  for (const [id, d] of downDist) {
    if (id === focus) continue;
    const u = upDist.get(id);
    if (u === void 0 || d <= u) layer.set(id, d);
  }
  return finishSubgraph(g, focus, layer);
}
function bfs(g, start, limit, next) {
  const dist = /* @__PURE__ */ new Map([[start, 0]]);
  const queue = [start];
  for (let i = 0; i < queue.length; i++) {
    const id = queue[i];
    const d = dist.get(id) ?? 0;
    if (d >= limit) continue;
    for (const n of next(g, id)) {
      if (dist.has(n)) continue;
      dist.set(n, d + 1);
      queue.push(n);
    }
  }
  return dist;
}
function forest(g) {
  const cmp = compareIds(g);
  const all = [...g.nodes.keys()].sort(cmp);
  const layer = /* @__PURE__ */ new Map();
  const roots = all.filter((id) => parentsOf(g, id).length === 0);
  const seed = (starts) => {
    const queue = [];
    for (const r2 of starts) {
      if (layer.has(r2)) continue;
      layer.set(r2, 0);
      queue.push(r2);
    }
    for (let i = 0; i < queue.length; i++) {
      const id = queue[i];
      const d = layer.get(id) ?? 0;
      for (const c of childrenOf(g, id)) {
        if (layer.has(c)) continue;
        layer.set(c, d + 1);
        queue.push(c);
      }
    }
  };
  seed(roots);
  for (const id of all) {
    if (layer.has(id)) continue;
    seed([id]);
  }
  return finishSubgraph(g, null, layer);
}
function finishSubgraph(g, focus, layer) {
  const cmp = compareIds(g);
  const ids = [...layer.keys()].sort((a, b) => {
    const la = layer.get(a) ?? 0;
    const lb = layer.get(b) ?? 0;
    return la !== lb ? la - lb : cmp(a, b);
  });
  const edges = [];
  for (const id of ids) {
    for (const c of childrenOf(g, id)) if (layer.has(c)) edges.push({ parent: id, child: c });
  }
  const roots = ids.filter((id) => {
    const l = layer.get(id) ?? 0;
    return !parentsOf(g, id).some((p) => (layer.get(p) ?? Number.POSITIVE_INFINITY) < l);
  });
  return { focus, ids, layer, edges, roots };
}
function pathToRoot(g, id) {
  const path = [id];
  const seen = /* @__PURE__ */ new Set([id]);
  let cur = id;
  for (; ; ) {
    const next = parentsOf(g, cur).find((p) => !seen.has(p));
    if (!next) break;
    seen.add(next);
    path.push(next);
    cur = next;
  }
  return path.reverse();
}
function outline(g, sub) {
  const build = (id, under, path) => {
    const alsoUnder = parentsOf(g, id).filter((p) => p !== under);
    if (path.has(id)) return { id, alsoUnder, cycle: true, cross: false, children: [] };
    const layer = sub.layer.get(id) ?? 0;
    if (under !== null && layer <= (sub.layer.get(under) ?? 0)) return { id, alsoUnder, cycle: false, cross: true, children: [] };
    path.add(id);
    const children = childrenOf(g, id).filter((c) => sub.layer.has(c)).map((c) => build(c, id, path));
    path.delete(id);
    return { id, alsoUnder, cycle: false, cross: false, children };
  };
  return sub.roots.map((r2) => build(r2, null, /* @__PURE__ */ new Set()));
}

// src/breadcrumbs.ts
var HOSTS = [".cm-sizer", ".markdown-preview-sizer"];
var Breadcrumbs = class {
  constructor(plugin) {
    this.plugin = plugin;
  }
  start() {
    const ws = this.plugin.app.workspace;
    this.plugin.registerEvent(ws.on("active-leaf-change", () => this.refreshAll()));
    this.plugin.registerEvent(ws.on("file-open", () => this.refreshAll()));
    this.plugin.registerEvent(ws.on("layout-change", () => this.refreshAll()));
    this.plugin.registerEvent(this.plugin.graph.on("changed", () => this.refreshAll()));
    this.plugin.register(() => this.removeAll());
    ws.onLayoutReady(() => this.refreshAll());
  }
  refreshAll() {
    if (!this.plugin.settings.showBreadcrumbs) {
      this.removeAll();
      return;
    }
    this.plugin.app.workspace.iterateAllLeaves((leaf) => {
      if (leaf.view instanceof import_obsidian.MarkdownView) this.refresh(leaf.view);
    });
  }
  removeAll() {
    this.plugin.app.workspace.iterateAllLeaves((leaf) => {
      if (!(leaf.view instanceof import_obsidian.MarkdownView)) return;
      for (const bar of Array.from(leaf.view.containerEl.querySelectorAll(".btoc-breadcrumbs"))) bar.remove();
    });
  }
  refresh(view) {
    const g = this.plugin.graph.graph;
    const file = view.file;
    const trail = file && g.nodes.has(file.path) && parentsOf(g, file.path).length > 0 ? pathToRoot(g, file.path) : null;
    for (const selector of HOSTS) {
      const host = view.containerEl.querySelector(selector);
      if (!host) continue;
      let bar = null;
      for (const child of Array.from(host.children)) {
        if (child.instanceOf(HTMLElement) && child.hasClass("btoc-breadcrumbs")) bar = child;
      }
      if (!trail) {
        bar?.remove();
        continue;
      }
      if (!bar) {
        bar = createDiv({ cls: "btoc-breadcrumbs" });
        host.prepend(bar);
      }
      const signature = trail.join("\0");
      if (bar.dataset.trail === signature) continue;
      bar.dataset.trail = signature;
      this.fill(bar, trail, view);
    }
  }
  fill(bar, trail, view) {
    const g = this.plugin.graph.graph;
    bar.empty();
    const icon = bar.createSpan({ cls: "btoc-crumb-icon" });
    (0, import_obsidian.setIcon)(icon, "git-fork");
    trail.forEach((id, i) => {
      if (i > 0) bar.createSpan({ cls: "btoc-crumb-sep", text: "\u203A" });
      const current = i === trail.length - 1;
      const crumb = bar.createSpan({ cls: "btoc-crumb", text: nameOf(g, id) });
      if (current) crumb.addClass("is-current");
      else {
        crumb.setAttribute("role", "link");
        crumb.setAttribute("tabindex", "0");
        crumb.addEventListener("click", (evt) => void this.plugin.openNode(id, evt));
        crumb.addEventListener("auxclick", (evt) => {
          if (evt.button === 1) void this.plugin.openNode(id, evt);
        });
        crumb.addEventListener("mouseover", (evt) => this.plugin.hoverNode(id, evt, crumb, view));
        crumb.addEventListener("contextmenu", (evt) => {
          evt.preventDefault();
          this.plugin.nodeMenu(id, evt);
        });
      }
      const next = trail[i + 1];
      if (next === void 0) return;
      const others = parentsOf(g, next).filter((p) => p !== id);
      if (!others.length) return;
      const more = bar.createSpan({
        cls: "btoc-crumb-more",
        text: `+${others.length}`,
        attr: { "aria-label": `Other parents of ${nameOf(g, next)}`, role: "button", tabindex: "0" }
      });
      more.addEventListener("click", (evt) => {
        const menu = new import_obsidian.Menu();
        for (const p of others) {
          menu.addItem(
            (item) => item.setTitle(nameOf(g, p)).setIcon("file").onClick((e) => void this.plugin.openNode(p, e instanceof MouseEvent ? e : evt))
          );
        }
        menu.showAtMouseEvent(evt);
      });
    });
  }
};

// src/codeblock.ts
var import_obsidian4 = require("obsidian");

// src/layout.ts
var DEFAULT_LAYOUT = {
  orientation: "top-down",
  style: "boxes",
  nodeHeight: 32,
  charWidth: 7.4,
  paddingX: 14,
  minWidth: 64,
  maxWidth: 240,
  maxChars: 30,
  gap: 16,
  layerGap: 56,
  margin: 24
};
function truncateLabel(name, maxChars) {
  if (name.length <= maxChars) return name;
  return name.slice(0, Math.max(1, maxChars - 1)).trimEnd() + "\u2026";
}
function layout(g, sub, options = {}) {
  const o = { ...DEFAULT_LAYOUT, ...options };
  const lr = o.orientation === "left-right";
  const phylo = o.style === "phylogenetic";
  const cmp = compareIds(g);
  const widthOf = /* @__PURE__ */ new Map();
  const labelOf = /* @__PURE__ */ new Map();
  for (const id of sub.ids) {
    const label = truncateLabel(nameOf(g, id), o.maxChars);
    labelOf.set(id, label);
    widthOf.set(id, Math.min(o.maxWidth, Math.max(o.minWidth, label.length * o.charWidth + 2 * o.paddingX)));
  }
  const ext = (id) => lr ? o.nodeHeight : widthOf.get(id) ?? o.minWidth;
  const primaryParent = /* @__PURE__ */ new Map();
  const primaryChild = /* @__PURE__ */ new Map();
  for (const id of sub.ids) {
    const l = sub.layer.get(id) ?? 0;
    if (l > 0) {
      const p = parentsOf(g, id).find((x) => sub.layer.get(x) === l - 1);
      if (p) primaryParent.set(id, p);
    } else if (l < 0) {
      const c = childrenOf(g, id).find((x) => sub.layer.get(x) === l + 1);
      if (c) primaryChild.set(id, c);
    }
  }
  const treeKidsDown = /* @__PURE__ */ new Map();
  const treeKidsUp = /* @__PURE__ */ new Map();
  for (const [child, parent] of primaryParent) pushTo(treeKidsDown, parent, child);
  for (const [parent, child] of primaryChild) pushTo(treeKidsUp, child, parent);
  for (const list of treeKidsDown.values()) list.sort(cmp);
  for (const list of treeKidsUp.values()) list.sort(cmp);
  const cross = /* @__PURE__ */ new Map();
  const buildBox = (id, kidsOf) => {
    const kids = (kidsOf.get(id) ?? []).map((k) => buildBox(k, kidsOf));
    const span = kids.length ? kids.reduce((s, k) => s + k.width, 0) + o.gap * (kids.length - 1) : 0;
    return { id, ext: ext(id), cross: 0, kids, width: Math.max(ext(id), span) };
  };
  const place = (box, left) => {
    cross.set(box.id, left + box.width / 2);
    if (!box.kids.length) return;
    const span = box.kids.reduce((s, k) => s + k.width, 0) + o.gap * (box.kids.length - 1);
    let x = left + (box.width - span) / 2;
    for (const kid of box.kids) {
      place(kid, x);
      x += kid.width + o.gap;
    }
  };
  if (sub.focus !== null) {
    const down = buildBox(sub.focus, treeKidsDown);
    const up = buildBox(sub.focus, treeKidsUp);
    place(down, -down.width / 2);
    const focusX = cross.get(sub.focus) ?? 0;
    place(up, -up.width / 2);
    const shift = focusX - (cross.get(sub.focus) ?? 0);
    for (const id of sub.ids) {
      if ((sub.layer.get(id) ?? 0) < 0) cross.set(id, (cross.get(id) ?? 0) + shift);
    }
    cross.set(sub.focus, focusX);
  } else {
    let x = 0;
    for (const r2 of sub.roots) {
      const box = buildBox(r2, treeKidsDown);
      place(box, x);
      x += box.width + o.gap;
    }
  }
  let fallback = Math.max(0, ...[...cross.values()]) + o.gap;
  for (const id of sub.ids) {
    if (cross.has(id)) continue;
    cross.set(id, fallback + ext(id) / 2);
    fallback += ext(id) + o.gap;
  }
  const layers = [...new Set(sub.ids.map((id) => sub.layer.get(id) ?? 0))].sort((a, b) => a - b);
  const layerExtent = /* @__PURE__ */ new Map();
  for (const l of layers) {
    const members = sub.ids.filter((id) => sub.layer.get(id) === l);
    layerExtent.set(l, lr ? Math.max(...members.map((id) => widthOf.get(id) ?? o.minWidth)) : o.nodeHeight);
  }
  const layerPos = /* @__PURE__ */ new Map();
  let pos = 0;
  for (const l of layers) {
    const e = layerExtent.get(l) ?? o.nodeHeight;
    layerPos.set(l, pos + e / 2);
    pos += e + o.layerGap;
  }
  const deepest = layers.length ? layers[layers.length - 1] : 0;
  const tipStart = (layerPos.get(deepest) ?? 0) - (layerExtent.get(deepest) ?? o.nodeHeight) / 2;
  const isTip = (id) => phylo && deepest > 0 && (sub.layer.get(id) ?? 0) > 0 && !(treeKidsDown.get(id)?.length ?? 0);
  const alongOf = (id) => {
    if (!isTip(id)) return layerPos.get(sub.layer.get(id) ?? 0) ?? 0;
    return lr ? tipStart + (widthOf.get(id) ?? o.minWidth) / 2 : layerPos.get(deepest) ?? 0;
  };
  let minCross = Number.POSITIVE_INFINITY;
  let maxCross = Number.NEGATIVE_INFINITY;
  for (const id of sub.ids) {
    const c = cross.get(id) ?? 0;
    minCross = Math.min(minCross, c - ext(id) / 2);
    maxCross = Math.max(maxCross, c + ext(id) / 2);
  }
  if (!Number.isFinite(minCross)) {
    minCross = 0;
    maxCross = 0;
  }
  const nodes = sub.ids.map((id) => {
    const l = sub.layer.get(id) ?? 0;
    const c = (cross.get(id) ?? 0) - minCross + o.margin;
    const lp = alongOf(id) + o.margin;
    const w = widthOf.get(id) ?? o.minWidth;
    const node = g.nodes.get(id);
    return {
      id,
      label: labelOf.get(id) ?? id,
      name: nameOf(g, id),
      missing: node?.missing ?? false,
      layer: l,
      x: lr ? lp : c,
      y: lr ? c : lp,
      w,
      h: o.nodeHeight,
      hiddenParents: parentsOf(g, id).filter((p) => !sub.layer.has(p)),
      hiddenChildren: childrenOf(g, id).filter((p) => !sub.layer.has(p))
    };
  });
  const byId = new Map(nodes.map((n) => [n.id, n]));
  const edges = sub.edges.map((e) => {
    const p = byId.get(e.parent);
    const c = byId.get(e.child);
    if (!p || !c) return { ...e, d: "", irregular: true };
    const irregular = c.layer !== p.layer + 1;
    const forward = lr ? c.x - c.w / 2 > p.x + p.w / 2 : c.y - c.h / 2 > p.y + p.h / 2;
    return { ...e, d: phylo && forward ? elbowPath(p, c, lr, o.layerGap) : edgePath(p, c, lr, o.layerGap), irregular };
  });
  const crossSize = maxCross - minCross + 2 * o.margin;
  const layerSize = pos - o.layerGap + 2 * o.margin;
  return { nodes, edges, width: lr ? layerSize : crossSize, height: lr ? crossSize : layerSize, style: o.style };
}
function elbowPath(p, c, lr, layerGap) {
  const f = (n) => Math.round(n * 100) / 100;
  if (lr) {
    const sx = p.x + p.w / 2;
    const ex = c.x - c.w / 2;
    const bar2 = Math.min(sx + layerGap / 2, ex);
    return `M ${f(sx)} ${f(p.y)} H ${f(bar2)} V ${f(c.y)} H ${f(ex)}`;
  }
  const sy = p.y + p.h / 2;
  const ey = c.y - c.h / 2;
  const bar = Math.min(sy + layerGap / 2, ey);
  return `M ${f(p.x)} ${f(sy)} V ${f(bar)} H ${f(c.x)} V ${f(ey)}`;
}
function edgePath(p, c, lr, layerGap) {
  const f = (n) => Math.round(n * 100) / 100;
  if (lr) {
    const sx2 = p.x + p.w / 2;
    const sy2 = p.y;
    const ex2 = c.x - c.w / 2;
    const ey2 = c.y;
    const bend2 = ex2 > sx2 ? (ex2 - sx2) / 2 : layerGap;
    return `M ${f(sx2)} ${f(sy2)} C ${f(sx2 + bend2)} ${f(sy2)}, ${f(ex2 - bend2)} ${f(ey2)}, ${f(ex2)} ${f(ey2)}`;
  }
  const sx = p.x;
  const sy = p.y + p.h / 2;
  const ex = c.x;
  const ey = c.y - c.h / 2;
  const bend = ey > sy ? (ey - sy) / 2 : layerGap;
  return `M ${f(sx)} ${f(sy)} C ${f(sx)} ${f(sy + bend)}, ${f(ex)} ${f(ey - bend)}, ${f(ex)} ${f(ey)}`;
}
function pushTo(map, key, value) {
  const list = map.get(key);
  if (list) list.push(value);
  else map.set(key, [value]);
}

// src/links.ts
function cleanLink(raw) {
  let s = raw.trim();
  if (s.length >= 2 && (s.startsWith('"') && s.endsWith('"') || s.startsWith("'") && s.endsWith("'"))) s = s.slice(1, -1).trim();
  const md = /^\[[^\]]*\]\(([^)]+)\)$/.exec(s);
  if (md) {
    s = md[1].trim();
    if (s.startsWith("<") && s.endsWith(">")) s = s.slice(1, -1);
    try {
      s = decodeURIComponent(s);
    } catch {
    }
  }
  if (s.startsWith("[[") && s.endsWith("]]")) s = s.slice(2, -2);
  s = s.split("|")[0].split("#")[0].trim();
  if (s.toLowerCase().endsWith(".md")) s = s.slice(0, -3);
  return s;
}
function flattenStrings(value) {
  if (typeof value === "string") return [value];
  if (typeof value === "number") return [String(value)];
  if (Array.isArray(value)) return value.flatMap(flattenStrings);
  return [];
}
function propertyLinks(frontmatter, frontmatterLinks, keys) {
  const out = [];
  const seen = /* @__PURE__ */ new Set();
  const add = (raw) => {
    const link = cleanLink(raw);
    if (!link) return;
    const k = link.toLowerCase();
    if (seen.has(k)) return;
    seen.add(k);
    out.push(link);
  };
  for (const l of frontmatterLinks ?? []) {
    if (keys.includes(l.key.split(".")[0].toLowerCase())) add(l.link);
  }
  if (frontmatter) {
    for (const [key, value] of Object.entries(frontmatter)) {
      if (!keys.includes(key.toLowerCase())) continue;
      for (const s of flattenStrings(value)) add(s);
    }
  }
  return out;
}
function propertyNumber(frontmatter, key) {
  if (!frontmatter || !key) return void 0;
  const k = key.toLowerCase();
  for (const [name, value] of Object.entries(frontmatter)) {
    if (name.toLowerCase() !== k) continue;
    const n = typeof value === "number" ? value : typeof value === "string" ? parseFloat(value) : NaN;
    return Number.isFinite(n) ? n : void 0;
  }
  return void 0;
}
function linkDisplayName(link) {
  const parts = link.split("/").filter((p) => p.length > 0);
  return parts[parts.length - 1] ?? link;
}

// src/render-outline.ts
var import_obsidian2 = require("obsidian");
function outlineKey(parentKey, id) {
  return parentKey ? parentKey + "\0" + id : id;
}
function renderOutline(container, g, items, actions, opts) {
  const root = container.createDiv({ cls: "btoc-outline" });
  renderItems(root, g, items, actions, opts, "");
}
function renderItems(parent, g, items, actions, opts, parentKey) {
  for (const item of items) {
    const key = outlineKey(parentKey, item.id);
    const node = g.nodes.get(item.id);
    const el = parent.createDiv({ cls: "btoc-item" });
    const row = el.createDiv({ cls: "btoc-row" });
    if (item.id === opts.focus) row.addClass("is-focus");
    if (node?.missing) row.addClass("is-missing");
    const hasChildren = item.children.length > 0;
    const chevron = row.createDiv({ cls: "btoc-chevron" });
    if (hasChildren) {
      chevron.addClass("is-active");
      chevron.setAttribute("role", "button");
      chevron.setAttribute("aria-label", "Collapse or expand");
      (0, import_obsidian2.setIcon)(chevron, "chevron-down");
    }
    const link = row.createSpan({ cls: "btoc-link", text: nameOf(g, item.id) });
    link.setAttribute("role", "link");
    link.setAttribute("tabindex", "0");
    if (item.alsoUnder.length) {
      row.createSpan({ cls: "btoc-meta", text: "also under " + item.alsoUnder.map((p) => nameOf(g, p)).join(", ") });
    } else if (item.cross) {
      row.createSpan({ cls: "btoc-meta", text: "see above" });
    }
    if (item.cycle) row.createSpan({ cls: "btoc-meta is-cycle", text: "cycle" });
    if (node?.missing) row.createSpan({ cls: "btoc-meta", text: "missing" });
    link.addEventListener("click", (evt) => {
      evt.preventDefault();
      actions.open(item.id, evt);
    });
    link.addEventListener("auxclick", (evt) => {
      if (evt.button !== 1) return;
      evt.preventDefault();
      actions.open(item.id, evt);
    });
    link.addEventListener("keydown", (evt) => {
      if (evt.key === "Enter" || evt.key === " ") {
        evt.preventDefault();
        actions.open(item.id, new MouseEvent("click", { ctrlKey: evt.ctrlKey, metaKey: evt.metaKey }));
      }
    });
    link.addEventListener("mouseover", (evt) => actions.hover(item.id, evt, link));
    row.addEventListener("contextmenu", (evt) => {
      evt.preventDefault();
      actions.menu(item.id, evt);
    });
    if (hasChildren) {
      const children = el.createDiv({ cls: "btoc-children" });
      const setCollapsed = (collapsed) => {
        el.toggleClass("is-collapsed", collapsed);
        if (collapsed) opts.collapsed.add(key);
        else opts.collapsed.delete(key);
      };
      setCollapsed(opts.collapsed.has(key));
      chevron.addEventListener("click", (evt) => {
        evt.preventDefault();
        setCollapsed(!el.hasClass("is-collapsed"));
      });
      renderItems(children, g, item.children, actions, opts, key);
    }
  }
}

// src/render-tree.ts
var import_obsidian3 = require("obsidian");
var MIN_SCALE = 0.15;
var MAX_SCALE = 3;
var DRAG_THRESHOLD = 4;
var TreeCanvas = class extends import_obsidian3.Component {
  constructor(container, lay, actions, focus) {
    super();
    this.container = container;
    this.lay = lay;
    this.actions = actions;
    this.focus = focus;
  }
  svg = null;
  viewport = null;
  scale = 1;
  tx = 0;
  ty = 0;
  dragging = null;
  suppressClick = false;
  observer = null;
  onload() {
    const svg = this.container.createSvg("svg", { cls: "btoc-svg" });
    svg.setAttribute("role", "img");
    svg.setAttribute("aria-label", "Branching table of contents");
    if (this.lay.style === "phylogenetic") svg.addClass("is-phylo");
    this.svg = svg;
    const viewport = svg.createSvg("g", { cls: "btoc-viewport" });
    this.viewport = viewport;
    const edges = viewport.createSvg("g", { cls: "btoc-edges" });
    const nodes = viewport.createSvg("g", { cls: "btoc-nodes" });
    for (const e of this.lay.edges) {
      if (!e.d) continue;
      const path = edges.createSvg("path", { cls: "btoc-edge" });
      path.setAttribute("d", e.d);
      if (e.irregular) path.addClass("is-irregular");
    }
    for (const n of this.lay.nodes) this.drawNode(nodes, n);
    this.on(svg, "wheel", (evt) => this.onWheel(evt), { passive: false });
    this.on(svg, "pointerdown", (evt) => this.onPointerDown(evt));
    this.on(svg, "pointermove", (evt) => this.onPointerMove(evt));
    this.on(svg, "pointerup", (evt) => this.onPointerUp(evt));
    this.on(svg, "pointercancel", (evt) => this.onPointerUp(evt));
    this.on(svg, "dblclick", (evt) => {
      const target = evt.target;
      if (!target?.closest(".btoc-node")) this.fit();
    });
    if (typeof ResizeObserver !== "undefined") {
      let first = true;
      this.observer = new ResizeObserver(() => {
        if (first && this.container.clientWidth > 0) {
          first = false;
          this.fit();
        }
      });
      this.observer.observe(this.container);
    }
    this.fit();
  }
  onunload() {
    this.observer?.disconnect();
    this.observer = null;
    this.svg?.remove();
    this.svg = null;
    this.viewport = null;
  }
  drawNode(parent, n) {
    const g = parent.createSvg("g", { cls: "btoc-node" });
    if (n.id === this.focus) g.addClass("is-focus");
    else if (n.layer < 0) g.addClass("is-ancestor");
    else g.addClass("is-descendant");
    if (n.missing) g.addClass("is-missing");
    g.setAttribute("transform", `translate(${r(n.x - n.w / 2)}, ${r(n.y - n.h / 2)})`);
    g.setAttribute("tabindex", "0");
    g.setAttribute("role", "link");
    g.setAttribute("aria-label", n.name);
    const rect = g.createSvg("rect", { cls: "btoc-node-box" });
    rect.setAttribute("width", r(n.w));
    rect.setAttribute("height", r(n.h));
    rect.setAttribute("rx", "7");
    rect.setAttribute("ry", "7");
    const text = g.createSvg("text", { cls: "btoc-node-label" });
    text.setAttribute("x", r(n.w / 2));
    text.setAttribute("y", r(n.h / 2));
    text.setAttribute("text-anchor", "middle");
    text.setAttribute("dominant-baseline", "central");
    text.textContent = n.label;
    if (n.label !== n.name) {
      const title = g.createSvg("title");
      title.textContent = n.name;
    }
    if (n.hiddenParents.length) this.drawBadge(g, n, n.hiddenParents, "top");
    if (n.hiddenChildren.length) this.drawBadge(g, n, n.hiddenChildren, "bottom");
    this.on(g, "click", (evt) => {
      if (this.suppressClick) return;
      evt.preventDefault();
      this.actions.open(n.id, evt);
    });
    this.on(g, "auxclick", (evt) => {
      if (evt.button !== 1 || this.suppressClick) return;
      evt.preventDefault();
      this.actions.open(n.id, evt);
    });
    this.on(g, "keydown", (evt) => {
      if (evt.key === "Enter" || evt.key === " ") {
        evt.preventDefault();
        this.actions.open(n.id, new MouseEvent("click", { ctrlKey: evt.ctrlKey, metaKey: evt.metaKey }));
      }
    });
    this.on(g, "mouseover", (evt) => this.actions.hover(n.id, evt, g));
    this.on(g, "contextmenu", (evt) => {
      evt.preventDefault();
      this.actions.menu(n.id, evt);
    });
  }
  /** Like registerDomEvent, but for SVG elements (which Obsidian's typing does not accept). */
  on(el, type, handler, options) {
    el.addEventListener(type, handler, options);
    this.register(() => el.removeEventListener(type, handler, options));
  }
  /** A small "+N" pill on the edge of a node for links that lie outside the drawn levels. */
  drawBadge(g, n, hidden, side) {
    const badge = g.createSvg("g", { cls: ["btoc-badge", side === "top" ? "is-top" : "is-bottom"] });
    const label = `+${hidden.length}`;
    const w = 10 + label.length * 6;
    const h = 14;
    const x = n.w - w - 4;
    const y = side === "top" ? -h / 2 : n.h - h / 2;
    badge.setAttribute("transform", `translate(${r(x)}, ${r(y)})`);
    const rect = badge.createSvg("rect");
    rect.setAttribute("width", r(w));
    rect.setAttribute("height", r(h));
    rect.setAttribute("rx", r(h / 2));
    const text = badge.createSvg("text");
    text.setAttribute("x", r(w / 2));
    text.setAttribute("y", r(h / 2));
    text.setAttribute("text-anchor", "middle");
    text.setAttribute("dominant-baseline", "central");
    text.textContent = label;
    const title = badge.createSvg("title");
    title.textContent = (side === "top" ? "More parents: " : "More children: ") + hidden.length;
  }
  fit() {
    if (!this.svg) return;
    const w = this.container.clientWidth;
    const h = this.container.clientHeight;
    if (w <= 0 || h <= 0 || this.lay.width <= 0 || this.lay.height <= 0) return;
    const scale = Math.min(w / this.lay.width, h / this.lay.height, 1.25);
    this.scale = Math.max(MIN_SCALE, scale);
    this.tx = (w - this.lay.width * this.scale) / 2;
    this.ty = (h - this.lay.height * this.scale) / 2;
    this.apply();
  }
  apply() {
    this.viewport?.setAttribute("transform", `translate(${r(this.tx)}, ${r(this.ty)}) scale(${r(this.scale)})`);
  }
  onWheel(evt) {
    evt.preventDefault();
    const rect = this.container.getBoundingClientRect();
    const cx = evt.clientX - rect.left;
    const cy = evt.clientY - rect.top;
    const factor = Math.exp(-evt.deltaY * (evt.deltaMode === 1 ? 0.05 : 15e-4));
    const next = Math.max(MIN_SCALE, Math.min(MAX_SCALE, this.scale * factor));
    const k = next / this.scale;
    this.tx = cx - (cx - this.tx) * k;
    this.ty = cy - (cy - this.ty) * k;
    this.scale = next;
    this.apply();
  }
  onPointerDown(evt) {
    if (evt.button !== 0 && evt.pointerType === "mouse") return;
    this.dragging = { id: evt.pointerId, x: evt.clientX, y: evt.clientY, tx: this.tx, ty: this.ty, moved: false };
    this.svg?.setPointerCapture(evt.pointerId);
  }
  onPointerMove(evt) {
    const d = this.dragging;
    if (!d || d.id !== evt.pointerId) return;
    const dx = evt.clientX - d.x;
    const dy = evt.clientY - d.y;
    if (!d.moved && Math.hypot(dx, dy) < DRAG_THRESHOLD) return;
    d.moved = true;
    this.svg?.addClass("is-panning");
    this.tx = d.tx + dx;
    this.ty = d.ty + dy;
    this.apply();
  }
  onPointerUp(evt) {
    const d = this.dragging;
    if (!d || d.id !== evt.pointerId) return;
    this.dragging = null;
    this.svg?.removeClass("is-panning");
    if (this.svg?.hasPointerCapture(evt.pointerId)) this.svg.releasePointerCapture(evt.pointerId);
    if (d.moved) {
      this.suppressClick = true;
      window.setTimeout(() => this.suppressClick = false, 0);
    }
  }
};
function r(n) {
  return String(Math.round(n * 100) / 100);
}

// src/settings.ts
var DEFAULT_SETTINGS = {
  parentProperties: "parent, parents",
  childProperties: "child, children",
  orderProperty: "toc-order",
  levelsUp: 2,
  levelsDown: 2,
  defaultMode: "tree",
  defaultScope: "focus",
  orientation: "top-down",
  treeStyle: "boxes",
  followActiveNote: true,
  showBreadcrumbs: true,
  showMissing: true,
  excludeFolders: "",
  newNoteLink: "new",
  tocNoteSuffix: " - Table of Contents",
  tocMaxDepth: 12
};
function parseList(value) {
  const out = [];
  for (const raw of value.split(/[,\n]/)) {
    const v = raw.trim().toLowerCase();
    if (v && !out.includes(v)) out.push(v);
  }
  return out;
}
function firstProperty(value, fallback) {
  const first = value.split(/[,\n]/).map((s) => s.trim()).find((s) => s.length > 0);
  return first ?? fallback;
}
function clampLevels(n, fallback) {
  const v = typeof n === "number" ? n : typeof n === "string" ? parseInt(n, 10) : NaN;
  if (!Number.isFinite(v)) return fallback;
  return Math.max(0, Math.min(10, Math.round(v)));
}

// src/codeblock.ts
var CODE_BLOCK = "branching-toc";
function parseBlockOptions(source) {
  const opts = { root: null, up: 0, down: 2, mode: "outline", orientation: null, style: null, height: 320 };
  for (const line of source.split("\n")) {
    const m = /^\s*([A-Za-z_-]+)\s*:\s*(.*?)\s*$/.exec(line);
    if (!m) continue;
    const key = m[1].toLowerCase();
    const value = m[2];
    switch (key) {
      case "root":
        opts.root = cleanLink(value.replace(/^["']|["']$/g, "")) || null;
        break;
      case "up":
        opts.up = clampLevels(value, opts.up);
        break;
      case "down":
      case "depth":
        opts.down = clampLevels(value, opts.down);
        break;
      case "mode":
        if (value === "tree" || value === "outline") opts.mode = value;
        break;
      case "orientation":
      case "direction":
        if (value === "top-down" || value === "left-right") opts.orientation = value;
        break;
      case "style":
        if (value === "boxes" || value === "family") opts.style = "boxes";
        else if (/^(phylogenetic|phylogenic|phylo|cladogram)$/i.test(value)) opts.style = "phylogenetic";
        break;
      case "height": {
        const h = parseInt(value, 10);
        if (Number.isFinite(h)) opts.height = Math.max(120, Math.min(2e3, h));
        break;
      }
    }
  }
  return opts;
}
function registerTocCodeBlock(plugin) {
  plugin.registerMarkdownCodeBlockProcessor(CODE_BLOCK, (source, el, ctx) => {
    ctx.addChild(new TocBlock(el, parseBlockOptions(source), ctx.sourcePath, plugin));
  });
}
var TocBlock = class extends import_obsidian4.MarkdownRenderChild {
  constructor(containerEl, opts, sourcePath, plugin) {
    super(containerEl);
    this.opts = opts;
    this.sourcePath = sourcePath;
    this.plugin = plugin;
    this.actions = {
      open: (id, evt) => void plugin.openNode(id, evt),
      hover: (id, evt, target) => plugin.hoverNode(id, evt, target, this),
      menu: (id, evt) => plugin.nodeMenu(id, evt)
    };
  }
  hoverPopover = null;
  canvas = null;
  collapsed = /* @__PURE__ */ new Set();
  actions;
  onload() {
    this.registerEvent(this.plugin.graph.on("changed", () => this.render()));
    this.render();
  }
  render() {
    if (this.canvas) {
      this.removeChild(this.canvas);
      this.canvas = null;
    }
    const el = this.containerEl;
    el.empty();
    el.addClass("btoc-block");
    const g = this.plugin.graph.graph;
    const root = this.opts.root ? this.plugin.app.metadataCache.getFirstLinkpathDest(this.opts.root, this.sourcePath)?.path ?? null : this.sourcePath;
    if (!root) {
      el.createDiv({ cls: "btoc-empty", text: `Cannot find the note "${this.opts.root ?? ""}".` });
      return;
    }
    if (!g.nodes.has(root)) {
      const name = this.plugin.graph.fileOf(root)?.basename ?? root;
      el.createDiv({ cls: "btoc-empty", text: `${name} has no parent or child links.` });
      return;
    }
    const sub = neighborhood(g, root, this.opts.up, this.opts.down);
    if (this.opts.mode === "tree") {
      const host = el.createDiv({ cls: "btoc-block-tree" });
      host.setCssProps({ height: `${this.opts.height}px` });
      const lay = layout(g, sub, { orientation: this.opts.orientation ?? this.plugin.settings.orientation, style: this.opts.style ?? this.plugin.settings.treeStyle });
      this.canvas = new TreeCanvas(host, lay, this.actions, root);
      this.addChild(this.canvas);
    } else {
      renderOutline(el, g, outline(g, sub), this.actions, { focus: root, collapsed: this.collapsed });
    }
  }
};

// src/export.ts
var import_obsidian6 = require("obsidian");

// src/related-notes.ts
var import_obsidian5 = require("obsidian");
function promptRelatedNote(plugin, current, relation) {
  const heading = relation === "child" ? `New child of ${current.basename}` : `New parent of ${current.basename}`;
  new TitleModal(plugin.app, heading, (title) => void createRelatedNote(plugin, current, title, relation)).open();
}
function sanitizeTitle(title) {
  return title.replace(/[\\/:*?"<>|#^[\]]/g, "").replace(/\s+/g, " ").trim();
}
async function createRelatedNote(plugin, current, title, relation) {
  const app = plugin.app;
  const name = sanitizeTitle(title);
  if (!name) {
    new import_obsidian5.Notice("That is not a valid note name.");
    return null;
  }
  const folder = current.parent && current.parent.path !== "/" ? current.parent.path + "/" : "";
  const path = (0, import_obsidian5.normalizePath)(`${folder}${name}.md`);
  if (app.vault.getAbstractFileByPath(path)) {
    new import_obsidian5.Notice(`A note named "${name}" already exists here.`);
    return null;
  }
  const file = await app.vault.create(path, "");
  const s = plugin.settings;
  const parentKey = firstProperty(s.parentProperties, "parent");
  const childKey = firstProperty(s.childProperties, "child");
  const linkToCurrent = `[[${app.metadataCache.fileToLinktext(current, file.path)}]]`;
  const linkToNew = `[[${app.metadataCache.fileToLinktext(file, current.path)}]]`;
  const onNew = s.newNoteLink !== "existing";
  const onCurrent = s.newNoteLink !== "new";
  if (relation === "child") {
    if (onNew) await app.fileManager.processFrontMatter(file, (fm) => addLink(fm, parentKey, linkToCurrent, false));
    if (onCurrent) await app.fileManager.processFrontMatter(current, (fm) => addLink(fm, childKey, linkToNew, true));
  } else {
    if (onNew) await app.fileManager.processFrontMatter(file, (fm) => addLink(fm, childKey, linkToCurrent, true));
    if (onCurrent) await app.fileManager.processFrontMatter(current, (fm) => addLink(fm, parentKey, linkToNew, false));
  }
  await app.workspace.getLeaf(false).openFile(file);
  return file;
}
function addLink(fm, key, link, asList) {
  const existing = flattenStrings(fm[key]).map((s) => s.trim()).filter((s) => s.length > 0);
  const target = cleanLink(link).toLowerCase();
  if (existing.some((s) => cleanLink(s).toLowerCase() === target)) return;
  if (asList || existing.length > 0) fm[key] = [...existing, link];
  else fm[key] = link;
}
var TitleModal = class extends import_obsidian5.Modal {
  constructor(app, heading, onSubmit) {
    super(app);
    this.heading = heading;
    this.onSubmit = onSubmit;
  }
  onOpen() {
    this.setTitle(this.heading);
    let value = "";
    const submit = () => {
      if (!value.trim()) return;
      this.close();
      this.onSubmit(value);
    };
    new import_obsidian5.Setting(this.contentEl).setName("Title").addText((t) => {
      t.setPlaceholder("Note title");
      t.onChange((v) => value = v);
      t.inputEl.addEventListener("keydown", (evt) => {
        if (evt.key === "Enter") {
          evt.preventDefault();
          submit();
        }
      });
      window.setTimeout(() => t.inputEl.focus(), 0);
    });
    new import_obsidian5.Setting(this.contentEl).addButton((b) => b.setButtonText("Create").setCta().onClick(submit));
  }
  onClose() {
    this.contentEl.empty();
  }
};
var ConfirmModal = class extends import_obsidian5.Modal {
  constructor(app, heading, body, confirmText, onConfirm) {
    super(app);
    this.heading = heading;
    this.body = body;
    this.confirmText = confirmText;
    this.onConfirm = onConfirm;
  }
  onOpen() {
    this.setTitle(this.heading);
    this.contentEl.createEl("p", { text: this.body });
    new import_obsidian5.Setting(this.contentEl).addButton((b) => b.setButtonText("Cancel").onClick(() => this.close())).addButton(
      (b) => b.setButtonText(this.confirmText).setCta().onClick(() => {
        this.close();
        this.onConfirm();
      })
    );
  }
  onClose() {
    this.contentEl.empty();
  }
};

// src/toc.ts
function tocMarkdown(g, root, opts) {
  const sub = neighborhood(g, root, 0, opts.maxDepth);
  const items = outline(g, sub);
  const lines = [];
  if (opts.heading) lines.push(`# ${opts.heading}`, "");
  const walk = (list, depth) => {
    for (const item of list) {
      lines.push("	".repeat(depth) + "- " + lineFor(g, item, opts));
      walk(item.children, depth + 1);
    }
  };
  walk(items, 0);
  return lines.join("\n") + "\n";
}
function lineFor(g, item, opts) {
  const node = g.nodes.get(item.id);
  const name = nameOf(g, item.id);
  const target = opts.link(item.id);
  let text = target === name ? `[[${target}]]` : `[[${target}|${name}]]`;
  const notes = [];
  if (node?.missing) notes.push("missing");
  if (item.alsoUnder.length) notes.push("also under " + item.alsoUnder.map((p) => nameOf(g, p)).join(", "));
  else if (item.cross) notes.push("see above");
  if (item.cycle) notes.push("cycle");
  if (notes.length) text += ` *(${notes.join("; ")})*`;
  return text;
}

// src/export.ts
function pickRootAndGenerate(plugin) {
  const g = plugin.graph.graph;
  const candidates = [...g.nodes.values()].filter((n) => !n.missing && childrenOf(g, n.id).length > 0).sort(compareNodes);
  if (candidates.length === 0) {
    new import_obsidian6.Notice("No note has children yet.");
    return;
  }
  new RootPickerModal(plugin.app, candidates, (n) => void generateTocNote(plugin, n.id)).open();
}
async function generateTocNote(plugin, rootId) {
  const app = plugin.app;
  const file = plugin.graph.fileOf(rootId);
  if (!file) return;
  const s = plugin.settings;
  const folder = file.parent && file.parent.path !== "/" ? file.parent.path + "/" : "";
  const path = (0, import_obsidian6.normalizePath)(`${folder}${file.basename}${s.tocNoteSuffix}.md`);
  if (path === file.path) {
    new import_obsidian6.Notice("The table of contents note would overwrite the root note; change the suffix in settings.");
    return;
  }
  const markdown = tocMarkdown(plugin.graph.graph, rootId, {
    maxDepth: Math.max(1, s.tocMaxDepth),
    heading: file.basename,
    link: (id) => plugin.graph.linkText(id, path)
  });
  const existing = app.vault.getFileByPath(path);
  const write = async () => {
    let out;
    if (existing) {
      await app.vault.modify(existing, markdown);
      out = existing;
    } else {
      out = await app.vault.create(path, markdown);
    }
    await app.workspace.getLeaf(false).openFile(out);
    new import_obsidian6.Notice(`Wrote ${out.basename}`);
  };
  if (existing) {
    new ConfirmModal(
      app,
      "Replace existing note?",
      `"${path}" already exists. Replace its contents with the new table of contents?`,
      "Replace",
      () => void write()
    ).open();
  } else {
    await write();
  }
}
var RootPickerModal = class extends import_obsidian6.FuzzySuggestModal {
  constructor(app, items, onPick) {
    super(app);
    this.items = items;
    this.onPick = onPick;
    this.setPlaceholder("Root note of the table of contents");
  }
  getItems() {
    return this.items;
  }
  getItemText(node) {
    return `${node.name}  (${node.id})`;
  }
  onChooseItem(node) {
    this.onPick(node);
  }
};

// src/settings-tab.ts
var import_obsidian7 = require("obsidian");
var BtocSettingTab = class extends import_obsidian7.PluginSettingTab {
  constructor(app, plugin) {
    super(app, plugin);
    this.plugin = plugin;
  }
  display() {
    const { containerEl } = this;
    containerEl.empty();
    const s = this.plugin.settings;
    const save = () => void this.plugin.saveSettings();
    new import_obsidian7.Setting(containerEl).setName("Properties").setHeading();
    new import_obsidian7.Setting(containerEl).setName("Parent properties").setDesc("Frontmatter properties that hold links to a note's parents. Comma-separated; the first one is written when you create a child note.").addText(
      (t) => t.setValue(s.parentProperties).onChange((v) => {
        s.parentProperties = v;
        save();
      })
    );
    new import_obsidian7.Setting(containerEl).setName("Child properties").setDesc("Frontmatter properties that hold links to a note's children. Comma-separated; the first one is written when you create a parent note.").addText(
      (t) => t.setValue(s.childProperties).onChange((v) => {
        s.childProperties = v;
        save();
      })
    );
    new import_obsidian7.Setting(containerEl).setName("Order property").setDesc("Optional numeric property used to sort siblings. Lower comes first; notes without it follow alphabetically.").addText(
      (t) => t.setValue(s.orderProperty).onChange((v) => {
        s.orderProperty = v;
        save();
      })
    );
    new import_obsidian7.Setting(containerEl).setName("Exclude folders").setDesc("Folders to ignore, one per line or comma-separated (for example Templates).").addTextArea(
      (t) => t.setValue(s.excludeFolders).onChange((v) => {
        s.excludeFolders = v;
        save();
      })
    );
    new import_obsidian7.Setting(containerEl).setName("Show missing notes").setDesc("Draw links to notes that do not exist yet as dashed boxes. Clicking one creates the note.").addToggle(
      (t) => t.setValue(s.showMissing).onChange((v) => {
        s.showMissing = v;
        save();
      })
    );
    new import_obsidian7.Setting(containerEl).setName("Tree view").setHeading();
    new import_obsidian7.Setting(containerEl).setName("Levels up").setDesc("How many generations above the current note to show (0 to 10).").addText((t) => {
      t.inputEl.type = "number";
      t.inputEl.min = "0";
      t.inputEl.max = "10";
      t.setValue(String(s.levelsUp)).onChange((v) => {
        s.levelsUp = clampLevels(v, s.levelsUp);
        save();
      });
    });
    new import_obsidian7.Setting(containerEl).setName("Levels down").setDesc("How many generations below the current note to show (0 to 10).").addText((t) => {
      t.inputEl.type = "number";
      t.inputEl.min = "0";
      t.inputEl.max = "10";
      t.setValue(String(s.levelsDown)).onChange((v) => {
        s.levelsDown = clampLevels(v, s.levelsDown);
        save();
      });
    });
    new import_obsidian7.Setting(containerEl).setName("Default view").addDropdown(
      (d) => d.addOptions({ tree: "Drawn tree", outline: "Outline" }).setValue(s.defaultMode).onChange((v) => {
        s.defaultMode = v;
        save();
      })
    );
    new import_obsidian7.Setting(containerEl).setName("Default scope").setDesc("Family tree of the current note, or every tree in the vault.").addDropdown(
      (d) => d.addOptions({ focus: "Current note", all: "Whole vault" }).setValue(s.defaultScope).onChange((v) => {
        s.defaultScope = v;
        save();
      })
    );
    new import_obsidian7.Setting(containerEl).setName("Tree style").setDesc("Family tree draws each note as a box on the row of its level, joined by curves. Phylogenetic tree draws right-angled branches and lines up the notes at the ends of the branches, like a cladogram; it reads best left to right.").addDropdown(
      (d) => d.addOptions({ boxes: "Family tree", phylogenetic: "Phylogenetic tree" }).setValue(s.treeStyle).onChange((v) => {
        s.treeStyle = v;
        save();
      })
    );
    new import_obsidian7.Setting(containerEl).setName("Layout direction").addDropdown(
      (d) => d.addOptions({ "top-down": "Top to bottom", "left-right": "Left to right" }).setValue(s.orientation).onChange((v) => {
        s.orientation = v;
        save();
      })
    );
    new import_obsidian7.Setting(containerEl).setName("Follow the active note").setDesc("Newly opened tree views re-centre on whichever note you open. You can pin a view from its toolbar.").addToggle(
      (t) => t.setValue(s.followActiveNote).onChange((v) => {
        s.followActiveNote = v;
        save();
      })
    );
    new import_obsidian7.Setting(containerEl).setName("Breadcrumbs").setHeading();
    new import_obsidian7.Setting(containerEl).setName("Show breadcrumbs").setDesc("Show the path from the root at the top of every note that has a parent.").addToggle(
      (t) => t.setValue(s.showBreadcrumbs).onChange((v) => {
        s.showBreadcrumbs = v;
        save();
      })
    );
    new import_obsidian7.Setting(containerEl).setName("New notes").setHeading();
    new import_obsidian7.Setting(containerEl).setName("Where to write the link").setDesc("When creating a child or parent note, which note gets the property that links the two.").addDropdown(
      (d) => d.addOptions({ new: "The new note", existing: "The existing note", both: "Both" }).setValue(s.newNoteLink).onChange((v) => {
        s.newNoteLink = v;
        save();
      })
    );
    new import_obsidian7.Setting(containerEl).setName("Table of contents note").setHeading();
    new import_obsidian7.Setting(containerEl).setName("Note name suffix").setDesc("Appended to the root note's name. The note is created in the root note's folder.").addText(
      (t) => t.setValue(s.tocNoteSuffix).onChange((v) => {
        s.tocNoteSuffix = v;
        save();
      })
    );
    new import_obsidian7.Setting(containerEl).setName("Maximum depth").setDesc("How many levels below the root to list (1 to 30).").addText((t) => {
      t.inputEl.type = "number";
      t.inputEl.min = "1";
      t.inputEl.max = "30";
      t.setValue(String(s.tocMaxDepth)).onChange((v) => {
        const n = parseInt(v, 10);
        if (Number.isFinite(n)) s.tocMaxDepth = Math.max(1, Math.min(30, n));
        save();
      });
    });
  }
};

// src/tree-view.ts
var import_obsidian8 = require("obsidian");
var VIEW_TYPE = "branching-toc-tree";
var TreeView = class extends import_obsidian8.ItemView {
  constructor(leaf, plugin) {
    super(leaf);
    this.plugin = plugin;
    const s = plugin.settings;
    this.state = {
      mode: s.defaultMode,
      scope: s.defaultScope,
      up: s.levelsUp,
      down: s.levelsDown,
      pinned: !s.followActiveNote,
      focus: plugin.app.workspace.getActiveFile()?.path ?? null
    };
  }
  hoverPopover = null;
  state;
  toolbarEl = null;
  bodyEl = null;
  canvas = null;
  collapsed = /* @__PURE__ */ new Set();
  buttons = /* @__PURE__ */ new Map();
  upInput = null;
  downInput = null;
  actions = {
    open: (id, evt) => void this.plugin.openNode(id, evt),
    hover: (id, evt, el) => this.plugin.hoverNode(id, evt, el, this),
    menu: (id, evt) => this.plugin.nodeMenu(id, evt)
  };
  getViewType() {
    return VIEW_TYPE;
  }
  getDisplayText() {
    return "Branching table of contents";
  }
  getIcon() {
    return "git-fork";
  }
  onOpen() {
    this.contentEl.addClass("btoc-view");
    this.toolbarEl = this.contentEl.createDiv({ cls: "btoc-toolbar" });
    this.bodyEl = this.contentEl.createDiv({ cls: "btoc-body" });
    this.buildToolbar(this.toolbarEl);
    this.registerEvent(this.plugin.graph.on("changed", () => this.render()));
    this.registerEvent(this.app.workspace.on("file-open", (file) => this.followActive(file?.path ?? null)));
    this.registerEvent(
      this.app.workspace.on("active-leaf-change", () => this.followActive(this.app.workspace.getActiveFile()?.path ?? null))
    );
    this.render();
    return Promise.resolve();
  }
  onClose() {
    if (this.canvas) {
      this.removeChild(this.canvas);
      this.canvas = null;
    }
    return Promise.resolve();
  }
  getState() {
    return { ...this.state };
  }
  async setState(state, result) {
    if (state && typeof state === "object") {
      const s = state;
      if (s.mode === "tree" || s.mode === "outline") this.state.mode = s.mode;
      if (s.scope === "focus" || s.scope === "all") this.state.scope = s.scope;
      if (s.up !== void 0) this.state.up = clampLevels(s.up, this.state.up);
      if (s.down !== void 0) this.state.down = clampLevels(s.down, this.state.down);
      if (typeof s.pinned === "boolean") this.state.pinned = s.pinned;
      if (typeof s.focus === "string") this.state.focus = s.focus;
    }
    if (this.bodyEl) this.render();
    await super.setState(state, result);
  }
  /** Centres the view on a note (used by "Reveal in tree"); keeps the pin as it is. */
  setFocus(id) {
    this.state.scope = "focus";
    this.state.focus = id;
    this.render();
    this.app.workspace.requestSaveLayout();
  }
  followActive(path) {
    if (!path || this.state.pinned || path === this.state.focus) return;
    this.state.focus = path;
    this.render();
    this.app.workspace.requestSaveLayout();
  }
  buildToolbar(bar) {
    const group = (parent) => parent.createDiv({ cls: "btoc-tb-group" });
    const button = (parent, key, icon, label, onClick) => {
      const el = parent.createEl("button", { cls: "btoc-tb-btn", attr: { "aria-label": label, title: label } });
      (0, import_obsidian8.setIcon)(el, icon);
      el.addEventListener("click", onClick);
      this.buttons.set(key, el);
      return el;
    };
    const set = (key, value) => {
      this.state[key] = value;
      this.render();
      this.app.workspace.requestSaveLayout();
    };
    const modes = group(bar);
    button(modes, "mode:tree", "git-fork", "Drawn tree", () => set("mode", "tree"));
    button(modes, "mode:outline", "list-tree", "Outline", () => set("mode", "outline"));
    button(modes, "style", "workflow", "Phylogenetic tree", () => {
      const s = this.plugin.settings;
      s.treeStyle = s.treeStyle === "phylogenetic" ? "boxes" : "phylogenetic";
      if (s.treeStyle === "phylogenetic" && this.state.mode !== "tree") this.state.mode = "tree";
      void this.plugin.saveSettings().then(() => this.render());
    });
    const scopes = group(bar);
    button(scopes, "scope:focus", "crosshair", "Family tree of the current note", () => set("scope", "focus"));
    button(scopes, "scope:all", "network", "Whole vault", () => set("scope", "all"));
    const levels = bar.createDiv({ cls: "btoc-tb-levels" });
    const level = (icon, label, key) => {
      const wrap = levels.createSpan({ cls: "btoc-tb-level", attr: { "aria-label": label, title: label } });
      (0, import_obsidian8.setIcon)(wrap.createSpan({ cls: "btoc-tb-level-icon" }), icon);
      const input = wrap.createEl("input", { type: "number", attr: { min: "0", max: "10", "aria-label": label } });
      input.addEventListener("change", () => set(key, clampLevels(input.value, this.state[key])));
      return input;
    };
    this.upInput = level("arrow-up", "Levels up", "up");
    this.downInput = level("arrow-down", "Levels down", "down");
    bar.createDiv({ cls: "btoc-tb-spacer" });
    const misc = group(bar);
    button(misc, "pinned", "pin", "Pin: stop following the active note", () => set("pinned", !this.state.pinned));
    button(misc, "fit", "maximize", "Fit to view", () => this.canvas?.fit());
  }
  syncToolbar() {
    const active = (key, on) => this.buttons.get(key)?.toggleClass("is-active", on);
    active("mode:tree", this.state.mode === "tree");
    active("mode:outline", this.state.mode === "outline");
    active("scope:focus", this.state.scope === "focus");
    active("scope:all", this.state.scope === "all");
    active("pinned", this.state.pinned);
    const phylo = this.plugin.settings.treeStyle === "phylogenetic";
    active("style", phylo && this.state.mode === "tree");
    const style = this.buttons.get("style");
    if (style) {
      const label = phylo ? "Phylogenetic tree (on): click for the family tree" : "Phylogenetic tree: draw the tree as a cladogram";
      style.setAttribute("aria-label", label);
      style.setAttribute("title", label);
    }
    const pin = this.buttons.get("pinned");
    if (pin) (0, import_obsidian8.setIcon)(pin, this.state.pinned ? "pin-off" : "pin");
    this.buttons.get("fit")?.toggleClass("is-hidden", this.state.mode !== "tree");
    if (this.upInput) this.upInput.value = String(this.state.up);
    if (this.downInput) this.downInput.value = String(this.state.down);
    const levelsVisible = this.state.scope === "focus";
    this.upInput?.parentElement?.parentElement?.toggleClass("is-hidden", !levelsVisible);
  }
  render() {
    const body = this.bodyEl;
    if (!body) return;
    this.syncToolbar();
    if (this.canvas) {
      this.removeChild(this.canvas);
      this.canvas = null;
    }
    body.empty();
    body.toggleClass("is-outline", this.state.mode === "outline");
    const g = this.plugin.graph.graph;
    const s = this.plugin.settings;
    const hint = `Add a "${firstProperty(s.parentProperties, "parent")}" or "${firstProperty(s.childProperties, "child")}" property holding a link to another note.`;
    let sub;
    if (this.state.scope === "focus") {
      const focus = this.state.focus;
      if (!focus) {
        this.message(body, "Open a note to see its family tree.", hint);
        return;
      }
      if (!g.nodes.has(focus)) {
        const name = this.plugin.graph.fileOf(focus)?.basename ?? focus;
        const el = this.message(body, `${name} has no parent or child links yet.`, hint);
        const btn = el.createEl("button", { text: "Show the whole vault" });
        btn.addEventListener("click", () => {
          this.state.scope = "all";
          this.render();
        });
        return;
      }
      sub = neighborhood(g, focus, this.state.up, this.state.down);
    } else {
      sub = forest(g);
      if (sub.ids.length === 0) {
        this.message(body, "No notes have parent or child properties yet.", hint);
        return;
      }
    }
    if (this.state.mode === "tree") {
      const lay = layout(g, sub, { orientation: s.orientation, style: s.treeStyle });
      this.canvas = new TreeCanvas(body, lay, this.actions, sub.focus);
      this.addChild(this.canvas);
    } else {
      renderOutline(body, g, outline(g, sub), this.actions, { focus: sub.focus, collapsed: this.collapsed });
    }
  }
  message(body, text, hint) {
    const el = body.createDiv({ cls: "btoc-empty" });
    el.createEl("p", { text });
    el.createEl("p", { cls: "btoc-hint", text: hint });
    return el;
  }
};

// src/vault-graph.ts
var import_obsidian9 = require("obsidian");
var MISSING_PREFIX = "missing:";
var VaultGraph = class extends import_obsidian9.Events {
  constructor(app, plugin, settings) {
    super();
    this.app = app;
    this.plugin = plugin;
    this.settings = settings;
  }
  graph = emptyGraph();
  /** Original link text for each missing node, for creating the note on click. */
  missingLinks = /* @__PURE__ */ new Map();
  timer = null;
  start() {
    const mc = this.app.metadataCache;
    this.plugin.registerEvent(mc.on("changed", () => this.schedule()));
    this.plugin.registerEvent(mc.on("deleted", () => this.schedule()));
    this.plugin.registerEvent(mc.on("resolved", () => this.schedule()));
    this.plugin.registerEvent(this.app.vault.on("rename", () => this.schedule()));
    this.plugin.registerEvent(this.app.vault.on("delete", () => this.schedule()));
    this.plugin.register(() => {
      if (this.timer !== null) window.clearTimeout(this.timer);
    });
    this.app.workspace.onLayoutReady(() => this.rebuild());
  }
  /** Coalesces bursts of cache events into one rebuild. */
  schedule() {
    if (this.timer !== null) window.clearTimeout(this.timer);
    this.timer = window.setTimeout(() => {
      this.timer = null;
      this.rebuild();
    }, 150);
  }
  rebuild() {
    const s = this.settings();
    const parentKeys = parseList(s.parentProperties);
    const childKeys = parseList(s.childProperties);
    const orderKey = s.orderProperty.trim();
    const excluded = parseList(s.excludeFolders).map((f) => f.replace(/^\/+|\/+$/g, "") + "/");
    const isExcluded = (path) => {
      const p = path.toLowerCase();
      return excluded.some((f) => f !== "/" && p.startsWith(f));
    };
    const mc = this.app.metadataCache;
    const nodes = /* @__PURE__ */ new Map();
    const edges = [];
    const missingLinks = /* @__PURE__ */ new Map();
    for (const file of this.app.vault.getMarkdownFiles()) {
      if (isExcluded(file.path)) continue;
      const cache = mc.getFileCache(file);
      const fm = cache?.frontmatter;
      const parents = propertyLinks(fm, cache?.frontmatterLinks, parentKeys);
      const children = propertyLinks(fm, cache?.frontmatterLinks, childKeys);
      if (parents.length === 0 && children.length === 0) continue;
      nodes.set(file.path, { id: file.path, name: file.basename, missing: false, order: propertyNumber(fm, orderKey) });
      const resolve = (link) => {
        const target = mc.getFirstLinkpathDest(link, file.path);
        if (target) {
          if (isExcluded(target.path)) return null;
          if (!nodes.has(target.path)) {
            const tc = mc.getFileCache(target);
            nodes.set(target.path, {
              id: target.path,
              name: target.basename,
              missing: false,
              order: propertyNumber(tc?.frontmatter, orderKey)
            });
          }
          return target.path;
        }
        if (!s.showMissing) return null;
        const id = MISSING_PREFIX + link.toLowerCase();
        if (!nodes.has(id)) {
          nodes.set(id, { id, name: linkDisplayName(link), missing: true });
          missingLinks.set(id, link);
        }
        return id;
      };
      for (const l of parents) {
        const id = resolve(l);
        if (id) edges.push({ parent: id, child: file.path });
      }
      for (const l of children) {
        const id = resolve(l);
        if (id) edges.push({ parent: file.path, child: id });
      }
    }
    const linked = /* @__PURE__ */ new Set();
    for (const e of edges) {
      linked.add(e.parent);
      linked.add(e.child);
    }
    this.graph = buildGraph([...nodes.values()].filter((n) => linked.has(n.id)), edges);
    this.missingLinks = missingLinks;
    this.trigger("changed");
  }
  isMissing(id) {
    return id.startsWith(MISSING_PREFIX);
  }
  /** The link text that was written for a missing node, e.g. `Courses/Physics`. */
  missingLink(id) {
    return this.missingLinks.get(id) ?? id.slice(MISSING_PREFIX.length);
  }
  fileOf(id) {
    if (this.isMissing(id)) return null;
    return this.app.vault.getFileByPath(id);
  }
  /** Text to put inside `[[...]]` when linking to a node from `sourcePath`. */
  linkText(id, sourcePath) {
    const file = this.fileOf(id);
    if (file) return this.app.metadataCache.fileToLinktext(file, sourcePath);
    return this.missingLink(id);
  }
};

// src/main.ts
var HOVER_SOURCE = "branching-table-of-contents";
var BtocPlugin = class extends import_obsidian10.Plugin {
  settings = { ...DEFAULT_SETTINGS };
  graph = new VaultGraph(this.app, this, () => this.settings);
  breadcrumbs = new Breadcrumbs(this);
  async onload() {
    await this.loadSettings();
    this.registerView(VIEW_TYPE, (leaf) => new TreeView(leaf, this));
    this.registerHoverLinkSource(HOVER_SOURCE, { display: "Branching table of contents", defaultMod: true });
    this.addRibbonIcon("git-fork", "Open branching table of contents", () => void this.activateView());
    this.addCommand({
      id: "open-tree-view",
      name: "Open tree view",
      callback: () => void this.activateView()
    });
    this.addCommand({
      id: "reveal-current-note",
      name: "Reveal current note in tree",
      checkCallback: (checking) => {
        const file = this.app.workspace.getActiveFile();
        if (!file) return false;
        if (!checking) void this.revealInTree(file.path);
        return true;
      }
    });
    this.addCommand({
      id: "create-child-note",
      name: "Create child note of current note",
      checkCallback: (checking) => {
        const file = this.app.workspace.getActiveFile();
        if (!file) return false;
        if (!checking) promptRelatedNote(this, file, "child");
        return true;
      }
    });
    this.addCommand({
      id: "create-parent-note",
      name: "Create parent note of current note",
      checkCallback: (checking) => {
        const file = this.app.workspace.getActiveFile();
        if (!file) return false;
        if (!checking) promptRelatedNote(this, file, "parent");
        return true;
      }
    });
    this.addCommand({
      id: "generate-toc-note",
      name: "Generate table of contents note",
      callback: () => pickRootAndGenerate(this)
    });
    registerTocCodeBlock(this);
    this.breadcrumbs.start();
    this.addSettingTab(new BtocSettingTab(this.app, this));
    this.graph.start();
  }
  async loadSettings() {
    const stored = await this.loadData();
    this.settings = { ...DEFAULT_SETTINGS, ...stored ?? {} };
  }
  async saveSettings() {
    await this.saveData(this.settings);
    this.graph.rebuild();
    this.breadcrumbs.refreshAll();
  }
  /** Opens (or reveals) the tree view in the right sidebar. */
  async activateView() {
    const ws = this.app.workspace;
    let leaf = ws.getLeavesOfType(VIEW_TYPE)[0] ?? null;
    if (!leaf) {
      leaf = ws.getRightLeaf(false);
      if (!leaf) return null;
      await leaf.setViewState({ type: VIEW_TYPE, active: true });
    }
    await ws.revealLeaf(leaf);
    return leaf.view instanceof TreeView ? leaf.view : null;
  }
  async revealInTree(id) {
    const view = await this.activateView();
    view?.setFocus(id);
  }
  /**
   * Opens a node's note in the main editor so it becomes the current note; a missing node's
   * note is created. Modifier keys (or middle click) open a new tab or split instead.
   */
  async openNode(id, evt) {
    const ws = this.app.workspace;
    const leaf = this.editorLeaf(import_obsidian10.Keymap.isModEvent(evt));
    const file = this.graph.fileOf(id);
    if (file) {
      await leaf.openFile(file, { active: true });
      return;
    }
    if (this.graph.isMissing(id)) {
      ws.setActiveLeaf(leaf, { focus: false });
      await ws.openLinkText(this.graph.missingLink(id), "", false);
    }
  }
  /**
   * The leaf to open notes in. A plain click goes to the most recently used tab in the main
   * area (never a sidebar leaf, which is where the click came from), or a new tab if there is
   * none or it is pinned. With a modifier, Obsidian's own new-tab/split behaviour applies.
   */
  editorLeaf(newLeaf) {
    const ws = this.app.workspace;
    if (newLeaf) return ws.getLeaf(newLeaf);
    const recent = ws.getMostRecentLeaf(ws.rootSplit);
    if (recent && !recent.getViewState().pinned) return recent;
    return ws.getLeaf("tab");
  }
  hoverNode(id, evt, target, parent) {
    const file = this.graph.fileOf(id);
    if (!file) return;
    this.app.workspace.trigger("hover-link", {
      event: evt,
      source: HOVER_SOURCE,
      hoverParent: parent,
      targetEl: target,
      linktext: file.path,
      sourcePath: ""
    });
  }
  nodeMenu(id, evt) {
    const menu = new import_obsidian10.Menu();
    const file = this.graph.fileOf(id);
    if (file) {
      menu.addItem(
        (i) => i.setTitle("Open in new tab").setIcon("file-plus").onClick(() => void this.app.workspace.getLeaf("tab").openFile(file))
      );
      menu.addItem(
        (i) => i.setTitle("Open to the right").setIcon("separator-vertical").onClick(() => void this.app.workspace.getLeaf("split").openFile(file))
      );
      menu.addSeparator();
      menu.addItem(
        (i) => i.setTitle("Show in tree").setIcon("git-fork").onClick(() => void this.revealInTree(id))
      );
      menu.addItem(
        (i) => i.setTitle("Create child note").setIcon("file-plus-2").onClick(() => promptRelatedNote(this, file, "child"))
      );
      menu.addItem(
        (i) => i.setTitle("Create parent note").setIcon("file-up").onClick(() => promptRelatedNote(this, file, "parent"))
      );
      menu.addSeparator();
      this.app.workspace.trigger("file-menu", menu, file, HOVER_SOURCE);
    } else {
      menu.addItem(
        (i) => i.setTitle("Create this note").setIcon("file-plus").onClick(() => void this.openNode(id, evt))
      );
    }
    menu.showAtMouseEvent(evt);
  }
};

/* nosourcemap */