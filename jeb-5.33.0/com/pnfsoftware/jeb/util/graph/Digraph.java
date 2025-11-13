package com.pnfsoftware.jeb.util.graph;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.core.exceptions.InterruptionException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.MultiList;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class Digraph {
   private static final ILogger logger = GlobalLog.getLogger(Digraph.class);
   private boolean done;
   private List vertices = new ArrayList();
   private TreeMap vertexmap = new TreeMap();
   private List edges = new ArrayList();
   private MultiList edgefrommap = new MultiList();
   private MultiList edgetomap = new MultiList();
   private List reachabilityIndices;

   public static Digraph create() {
      return new Digraph();
   }

   public List copyOfVertices() {
      ArrayList var1 = new ArrayList(this.getVertexCount());

      for (Digraph.V var3 : this.vertices) {
         var1.add(var3.clone());
      }

      return var1;
   }

   public List copyOfEdges() {
      ArrayList var1 = new ArrayList(this.getEdgeCount());

      for (Digraph.E var3 : this.edges) {
         var1.add(var3.clone());
      }

      return var1;
   }

   public int getVertexCount() {
      return this.vertices.size();
   }

   public List getVertices() {
      return this.vertices;
   }

   public Digraph.V getVertex(int var1) {
      return (Digraph.V)this.vertexmap.get(var1);
   }

   public Digraph.V getVertexByIndex(int var1) {
      return (Digraph.V)this.vertices.get(var1);
   }

   public String getVertexLabel(int var1) {
      return ((Digraph.V)this.vertexmap.get(var1)).getLabel();
   }

   public void setVertexLabel(int var1, String var2) {
      ((Digraph.V)this.vertexmap.get(var1)).setLabel(var2);
   }

   public void setVertexLabels(Object... var1) {
      for (byte var2 = 0; var2 < var1.length - 1; var2 += 2) {
         Object var3 = var1[var2];
         Object var4 = var1[var2 + 1];
         if (var3 instanceof Integer && var4 instanceof String) {
            this.setVertexLabel((Integer)var3, (String)var4);
         }
      }
   }

   public void removeVertex(Digraph.V var1, boolean var2) {
      Assert.a(this.vertices.indexOf(var1) == var1.index);
      ArrayList var3 = new ArrayList();

      for (Digraph.E var5 : this.edgefrommap.get(var1.index)) {
         var3.add(var5.getDst().id);
      }

      ArrayList var9 = new ArrayList();

      for (Digraph.E var6 : this.edgetomap.get(var1.index)) {
         var9.add(var6.getSrc().id);
      }

      for (Digraph.E var17 : this.edgetomap.get(var1.index)) {
         this.edgefrommap.removeMultipleElements(var17.src.index, var1x -> var1x.dst == var1);
      }

      for (Digraph.E var18 : this.edgefrommap.get(var1.index)) {
         this.edgetomap.removeMultipleElements(var18.dst.index, var1x -> var1x.src == var1);
      }

      for (Digraph.E var19 : this.edgefrommap.remove(var1.index)) {
         Assert.a(this.edges.remove(var19), "Edge " + var19 + " is not present");
      }

      for (Digraph.E var20 : this.edgetomap.remove(var1.index)) {
         Assert.a(this.edges.remove(var20), "Edge " + var20 + " is not present");
      }

      Assert.a(this.vertexmap.remove(var1.id) == var1);
      Assert.a(this.vertices.remove(var1.index) == var1);

      for (int var15 = var1.index; var15 < this.vertices.size(); var15++) {
         ((Digraph.V)this.vertices.get(var15)).index--;
      }

      if (var2) {
         for (int var21 : var9) {
            for (int var8 : var3) {
               if (this.getEdge(var21, var8) == null) {
                  this.addEdge(var21, var8, null);
               }
            }
         }
      }

      this.verify();
   }

   private void verify() {
      Assert.a(this.vertices.size() == this.vertexmap.size());
      Assert.a(this.edgefrommap.values().size() == this.edges.size(), this.edgefrommap.values().size() + " != " + this.edges.size());
      Assert.a(this.edgetomap.values().size() == this.edges.size(), this.edgetomap.values().size() + " != " + this.edges.size());
      if (!Licensing.isReleaseBuild()) {
         HashSet var1 = new HashSet();
         int var2 = 0;

         for (Digraph.V var4 : this.vertices) {
            Assert.a(var4.index == var2, "Unexpected index");
            var2++;
            Assert.a(this.vertexmap.get(var4.id) == var4, "Vertex is missing");
            Assert.a(var1.add(var4.id), "Duplicate id: " + var4.id);
         }

         for (Digraph.E var6 : this.edges) {
            Assert.a(this.vertices.contains(var6.src));
            Assert.a(this.vertices.contains(var6.dst), "Vertex id=" + var6.dst.id + " was not found");
         }
      }
   }

   public int getEdgeCount() {
      return this.edges.size();
   }

   public List getEdges() {
      return this.edges;
   }

   public Digraph.E getEdge(int var1) {
      return (Digraph.E)this.edges.get(var1);
   }

   public void removeEdge(Digraph.E var1) {
      Assert.a(this.edges.remove(var1));
      Assert.a(this.edgefrommap.removeElement(var1.src.index, var1));
      Assert.a(this.edgetomap.removeElement(var1.dst.index, var1));
   }

   public void removeEdge(int var1) {
      Digraph.E var2 = (Digraph.E)this.edges.remove(var1);
      Assert.a(this.edgefrommap.removeElement(var2.src.index, var2));
      Assert.a(this.edgetomap.removeElement(var2.dst.index, var2));
   }

   public Digraph.E getEdge(int var1, int var2) {
      Digraph.V var3 = (Digraph.V)this.vertexmap.get(var1);
      Assert.a(var3 != null, "Source vertex " + var1 + " does not exist");
      int var4 = var3.index;

      for (Digraph.E var6 : this.getEdgesFrom(var4)) {
         if (var6.dst.id == var2) {
            return var6;
         }
      }

      return null;
   }

   List getEdgesFrom(int var1) {
      return this.edgefrommap.get(var1);
   }

   List getEdgesTo(int var1) {
      return this.edgetomap.get(var1);
   }

   public Digraph v(int var1, Double var2, String var3) {
      this.addVertex(var1, var2, var3, true);
      return this;
   }

   public Digraph v(int var1, Double var2) {
      return this.v(var1, var2, null);
   }

   public Digraph v(int var1) {
      return this.v(var1, null, null);
   }

   private Digraph.V addVertex(int var1, Double var2, String var3, boolean var4) {
      this.verifyNotDone();
      Digraph.V var5 = (Digraph.V)this.vertexmap.get(var1);
      if (var5 != null) {
         if (var4) {
            throw new IllegalArgumentException("Vertex id " + var1 + " is already in use");
         }
      } else {
         var5 = new Digraph.V(this.vertices.size(), var1, var2, var3);
         this.vertices.add(var5);
         this.vertexmap.put(var1, var5);
      }

      return var5;
   }

   private Digraph.E addEdge(int var1, int var2, Double var3) {
      this.verifyNotDone();
      boolean var4 = true;
      Digraph.V var5 = (Digraph.V)this.vertexmap.get(var1);
      if (var5 == null) {
         var5 = this.addVertex(var1, null, null, true);
         var4 = false;
      }

      Digraph.V var6 = var5;
      if (var2 != var1) {
         var6 = (Digraph.V)this.vertexmap.get(var2);
         if (var6 == null) {
            var6 = this.addVertex(var2, null, null, true);
            var4 = false;
         }
      }

      Digraph.V var7 = var6;
      if (var4) {
         Assert.a(this.edgefrommap.findFirstElement(var5.index, var1x -> var1x.dst == var7) == null, Strings.ff("Edge %d->%d already exists", var1, var2));
      }

      Digraph.E var8 = new Digraph.E(var5, var7, var3);
      this.edges.add(var8);
      this.edgefrommap.put(var5.index, var8);
      this.edgetomap.put(var7.index, var8);
      return var8;
   }

   public Digraph e(int var1, int var2, Double var3) {
      this.addEdge(var1, var2, var3);
      return this;
   }

   public Digraph e(int var1, int var2) {
      return this.e(var1, var2, null);
   }

   public Digraph done() {
      this.verify();
      return this;
   }

   private void verifyNotDone() {
      if (this.done) {
         throw new IllegalStateException();
      }
   }

   public void resetEdgeBetweennessScores() {
      for (Digraph.E var2 : this.edges) {
         var2.ebscore = 0.0;
      }

      for (Digraph.V var4 : this.vertices) {
         var4.vcscore = 0.0;
      }
   }

   public List computeEdgeBetweenness() {
      this.resetEdgeBetweennessScores();

      for (int var1 = 0; var1 < this.getVertexCount(); var1++) {
         this.computeEdgeBetweennessFromSingleNode(var1);

         for (Digraph.E var3 : this.edges) {
            if (var3.score != null) {
               var3.ebscore = var3.ebscore + var3.score;
            }
         }

         for (Digraph.V var10 : this.vertices) {
            if (var10.score != null) {
               var10.vcscore = var10.vcscore + var10.score;
            }
         }

         if (Thread.interrupted()) {
            throw new InterruptionException();
         }
      }

      List var6 = this.copyOfEdges();
      Collections.sort(var6, (var0, var1x) -> -Double.compare(var0.ebscore, var1x.ebscore));
      StringBuilder var8 = new StringBuilder();

      for (Digraph.E var4 : var6) {
         Strings.ff(var8, "%s>%s:%.1f,", var4.src, var4.dst, var4.ebscore);
      }

      Object[] var10000 = new Object[]{var8.toString()};
      List var12 = this.copyOfVertices();
      Collections.sort(var12, (var0, var1x) -> -Double.compare(var0.vcscore, var1x.vcscore));
      var8 = new StringBuilder();

      for (Digraph.V var5 : var12) {
         Strings.ff(var8, "%s:%.1f,", var5, var5.vcscore);
      }

      var10000 = new Object[]{var8.toString()};
      ArrayList var14 = new ArrayList(this.getEdgeCount());

      for (int var15 = 0; var15 < this.getEdgeCount(); var15++) {
         var14.add(var15);
      }

      Collections.sort(var14, new Digraph$1(this));
      return var14;
   }

   public List getEdgeIndexesByDescendingBetweenness() {
      ArrayList var1 = new ArrayList(this.getEdgeCount());

      for (int var2 = 0; var2 < this.getEdgeCount(); var2++) {
         var1.add(var2);
      }

      var1.sort((var1x, var2x) -> -Double.compare(((Digraph.E)this.edges.get(var1x)).ebscore, ((Digraph.E)this.edges.get(var2x)).ebscore));
      return var1;
   }

   public List getVertexIndexesByDescendingCentrality() {
      ArrayList var1 = new ArrayList(this.getVertexCount());

      for (int var2 = 0; var2 < this.getVertexCount(); var2++) {
         var1.add(var2);
      }

      var1.sort((var1x, var2x) -> -Double.compare(((Digraph.V)this.vertices.get(var1x)).vcscore, ((Digraph.V)this.vertices.get(var2x)).vcscore));
      return var1;
   }

   private void computeEdgeBetweennessFromSingleNode(int var1) {
      for (Digraph.E var3 : this.edges) {
         var3.score = null;
      }

      for (Digraph.V var24 : this.vertices) {
         var24.score = 0.0;
      }

      int[] var23 = new int[this.getVertexCount()];
      var23[var1] = 1;
      ArrayList var25 = new ArrayList();
      HashSet var4 = new HashSet();
      ArrayList var5 = new ArrayList();
      var5.add(var1);
      HashSet var6 = new HashSet();

      while (!var5.isEmpty()) {
         ArrayList var7 = new ArrayList();

         for (int var9 : var5) {
            for (Digraph.E var11 : this.getEdgesFrom(var9)) {
               int var12 = var11.dst.index;
               if (!var4.contains(var12)) {
                  var7.add(var11);
                  var6.add(var12);
                  var23[var12] += var23[var9];
               }
            }
         }

         if (!var7.isEmpty()) {
            var25.add(var7);
         }

         var4.addAll(var6);
         var5 = new ArrayList(var6);
         var6.clear();
         if (Thread.interrupted()) {
            throw new InterruptionException();
         }
      }

      for (int var26 = var25.size() - 1; var26 >= 0; var26--) {
         List var28 = (List)var25.get(var26);

         for (int var30 = 0; var30 < var28.size(); var30++) {
            Digraph.E var32 = (Digraph.E)var28.get(var30);
            if (var32.score == null) {
               ArrayList var33 = new ArrayList();
               var33.add(var32);
               Digraph.V var34 = var32.dst;

               for (int var13 = var30 + 1; var13 < var28.size(); var13++) {
                  Digraph.E var14 = (Digraph.E)var28.get(var13);
                  if (var14.dst == var34) {
                     var33.add(var14);
                  }
               }

               double var35 = 0.0;

               for (Digraph.E var16 : var33) {
                  var35 += var23[var16.src.index];
               }

               double var36 = 1.0 + ((Digraph.V)this.vertices.get(var34.index)).score;

               for (Digraph.E var18 : var33) {
                  double var19 = var36 * (var23[var18.src.index] / var35);
                  var18.score = var19;
                  Digraph.V var21 = (Digraph.V)this.vertices.get(var18.src.index);
                  var21.score = var21.score + var19;
               }
            }
         }

         if (Thread.interrupted()) {
            throw new InterruptionException();
         }
      }

      StringBuilder var27 = new StringBuilder();

      for (Digraph.E var31 : this.edges) {
         if (var31.score != null) {
            Strings.ff(var27, "%s>%s:%.1f,", var31.src, var31.dst, var31.score);
         }
      }
   }

   public boolean isWeaklyConnected() {
      byte var1 = 0;
      HashSet var2 = new HashSet();
      var2.add(Integer.valueOf(var1));
      HashSet var3 = new HashSet(var2);

      while (!var2.isEmpty()) {
         HashSet var4 = new HashSet();

         for (int var6 : var2) {
            for (Digraph.E var8 : this.getEdgesFrom(var6)) {
               int var9 = var8.dst.index;
               if (!var3.contains(var9)) {
                  var4.add(var9);
                  var3.add(var9);
               }
            }

            for (Digraph.E var11 : this.getEdgesTo(var6)) {
               int var12 = var11.src.index;
               if (!var3.contains(var12)) {
                  var4.add(var12);
                  var3.add(var12);
               }
            }
         }

         var2 = var4;
      }

      return var3.size() == this.getVertexCount();
   }

   public List getWeaklyConnectedComponents() {
      ArrayList var1 = new ArrayList();
      HashSet var2 = new HashSet(this.getVertexCount());

      for (int var3 = 0; var3 < this.getVertexCount(); var3++) {
         var2.add(var3);
      }

      for (int var12 = 0; !var2.isEmpty(); var12++) {
         HashSet var4 = new HashSet();
         var4.add((Integer)var2.iterator().next());
         HashSet var5 = new HashSet(var4);

         while (!var4.isEmpty()) {
            HashSet var6 = new HashSet();

            for (int var8 : var4) {
               for (Digraph.E var10 : this.getEdgesFrom(var8)) {
                  int var11 = var10.dst.index;
                  if (!var5.contains(var11)) {
                     var6.add(var11);
                     var5.add(var11);
                  }
               }

               for (Digraph.E var18 : this.getEdgesTo(var8)) {
                  int var20 = var18.src.index;
                  if (!var5.contains(var20)) {
                     var6.add(var20);
                     var5.add(var20);
                  }
               }
            }

            var4 = var6;
         }

         if (var12 == 0 && var2.equals(var5)) {
            var1.add(this);
            break;
         }

         Digraph var13 = create();

         for (int var15 : var5) {
            Digraph.V var17 = (Digraph.V)this.vertices.get(var15);
            var13.addVertex(var17.id, var17.weight, var17.label, false);

            for (Digraph.E var21 : this.getEdgesFrom(var15)) {
               var13.e(var21.src.id, var21.dst.id, var21.weight);
            }
         }

         var1.add(var13.done());
         var2.removeAll(var5);
      }

      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("V=%s:E=%s", this.vertices, this.edges);
   }

   private void computeTransitiveClosure() {
      int var1 = this.getVertexCount();
      this.reachabilityIndices = new ArrayList(var1);

      for (int var2 = 0; var2 < var1; var2++) {
         HashSet var3 = new HashSet();
         ArrayList var4 = new ArrayList();
         HashSet var5 = new HashSet();
         var4.add(var2);

         while (!var4.isEmpty()) {
            for (int var7 : var4) {
               for (Digraph.E var9 : this.getEdgesFrom(var7)) {
                  int var10 = var9.dst.index;
                  if (!var3.contains(var10)) {
                     var5.add(var10);
                  }
               }
            }

            var3.addAll(var5);
            var4 = new ArrayList(var5);
            var5.clear();
         }

         this.reachabilityIndices.add(var3);
      }
   }

   public boolean isAdjacent(Digraph.V var1, Digraph.V var2) {
      for (Digraph.E var4 : this.getEdgesFrom(var1.index)) {
         if (var4.dst == var2) {
            return true;
         }
      }

      return false;
   }

   public boolean canReach(Digraph.V var1, Digraph.V var2) {
      if (this.isAdjacent(var1, var2)) {
         return true;
      } else {
         if (this.reachabilityIndices == null) {
            this.computeTransitiveClosure();
         }

         Set var3 = (Set)this.reachabilityIndices.get(var1.index);
         return var3 == null ? false : ((Set)this.reachabilityIndices.get(var1.index)).contains(var2.index);
      }
   }

   public Set getReachableVertices(int var1) {
      Digraph.V var2 = (Digraph.V)this.vertexmap.get(var1);
      Assert.a(var2 != null, "Vertex id does not exist: " + var1);
      if (this.reachabilityIndices == null) {
         this.computeTransitiveClosure();
      }

      Set var3 = (Set)this.reachabilityIndices.get(var2.index);
      HashSet var4 = new HashSet();

      for (int var6 : var3) {
         var4.add(((Digraph.V)this.vertices.get(var6)).id);
      }

      return var4;
   }

   public static Digraph load(File var0) throws IOException {
      return load(var0, null);
   }

   private static Digraph load(File var0, Boolean var1) throws IOException {
      Digraph var2 = create();

      for (String var4 : IO.readLines(var0)) {
         var4 = var4.trim();
         if (!var4.isEmpty() && !var4.startsWith("#")) {
            if (var4.startsWith(":")) {
               String var20 = var4.substring(1);
               if (var1 != null || !var20.equals("directed")) {
                  if (var1 != null || !var20.equals("undirected")) {
                     throw new RuntimeException("Unknown command: " + var20);
                  }

                  var1 = false;
               } else {
                  var1 = true;
               }
            } else if (var4.startsWith("[")) {
               int var19 = 0;

               while (var19 >= 0) {
                  boolean var21 = false;
                  int var22 = var4.indexOf("]", var19 + 1);
                  String var24 = var4.substring(var19 + 1, var22);
                  String[] var25 = Strings.splitall(var24, " ");
                  if (var25.length != 2) {
                     var25 = Strings.splitall(var24, ">");
                     if (var25.length != 2) {
                        throw new RuntimeException("Illegal edge definition: " + var24);
                     }

                     var21 = true;
                  }

                  if (var1 != null) {
                     var21 = var1;
                  }

                  int var26 = Integer.parseInt(var25[0]);
                  int var27 = Integer.parseInt(var25[1]);
                  var2.e(var26, var27);
                  if (!var21) {
                     var2.e(var27, var26);
                  }

                  var19 = var4.indexOf("[", var22 + 1);
               }
            } else {
               int var5 = 0;

               while (var5 < var4.length() && Character.isDigit(var4.charAt(var5))) {
                  var5++;
               }

               int var6 = Integer.parseInt(var4.substring(0, var5));
               char var7 = var5 < var4.length() ? var4.charAt(var5) : 0;
               if (var7 == '>') {
                  int var23 = Integer.parseInt(var4.substring(var5 + 1));
                  var2.e(var6, var23);
                  if (var1 != null && !var1) {
                     var2.e(var23, var6);
                  }
               } else if (var7 == ' ') {
                  int var8 = Integer.parseInt(var4.substring(var5 + 1));
                  var2.e(var6, var8);
                  if (var1 == null || !var1) {
                     var2.e(var8, var6);
                  }
               } else {
                  if (var7 != ':') {
                     throw new RuntimeException("Cannot parse line: " + var4);
                  }

                  Double var9 = null;
                  String var10 = null;

                  for (String var14 : var4.substring(var5 + 1).split(";")) {
                     String[] var15 = var14.split("=");
                     if (var15.length == 2) {
                        String var16 = var15[0];
                        switch (var16) {
                           case "w":
                              var9 = Double.parseDouble(var15[1]);
                              break;
                           case "l":
                              var10 = var15[1];
                              break;
                           default:
                              throw new RuntimeException("Unknown vertex attribute: " + var14);
                        }
                     }
                  }

                  var2.v(var6, var9, var10);
               }
            }
         }
      }

      var2.done();
      return var2;
   }

   public static class E {
      private Digraph.V src;
      private Digraph.V dst;
      private Double weight;
      private Double score;
      private Double ebscore;

      E(Digraph.V var1, Digraph.V var2, Double var3) {
         this.src = var1;
         this.dst = var2;
         this.weight = var3;
      }

      E(Digraph.V var1, Digraph.V var2) {
         this(var1, var2, null);
      }

      public Digraph.V getSrc() {
         return this.src;
      }

      public int getSrcId() {
         return this.src.id;
      }

      public int getSrcIndex() {
         return this.src.index;
      }

      public Digraph.V getDst() {
         return this.dst;
      }

      public int getDstId() {
         return this.dst.id;
      }

      public int getDstIndex() {
         return this.dst.index;
      }

      public Double getWeight() {
         return this.weight;
      }

      public Double getScore() {
         return this.score;
      }

      public Double getEdgeBetweennessScore() {
         return this.ebscore;
      }

      public Digraph.E clone() {
         Digraph.E var1 = new Digraph.E(this.src, this.dst, this.weight);
         var1.score = this.score;
         var1.ebscore = this.ebscore;
         return var1;
      }

      @Override
      public String toString() {
         return this.weight == null ? Strings.ff("%d>%d", this.src.id, this.dst.id) : Strings.ff("%d>%d(%f)", this.src.id, this.dst.id, this.weight);
      }
   }

   public class V {
      private int index;
      private final int id;
      private Double weight;
      private String label;
      private Double score;
      private Double vcscore;

      V(int var2, int var3, Double var4, String var5) {
         this.index = var2;
         this.id = var3;
         this.weight = var4;
         this.label = var5;
      }

      V(int var2, int var3) {
         this(var2, var3, null, null);
      }

      V(int var2) {
         this(var2, var2, null, null);
      }

      public int getId() {
         return this.id;
      }

      public Double getWeight() {
         return this.weight;
      }

      public void setWeight(Double var1) {
         this.weight = var1;
      }

      public String getLabel() {
         return this.label;
      }

      public void setLabel(String var1) {
         this.label = var1;
      }

      public Double getVertexCentralityScore() {
         return this.vcscore;
      }

      public void setVertexCentralityScore(Double var1) {
         this.vcscore = var1;
      }

      public void normalizeVertexCentralityScore(double var1) {
         if (this.vcscore != null && (var1 > 0.0 || var1 < 0.0)) {
            this.vcscore = this.vcscore / var1;
         }
      }

      public Digraph.V clone() {
         Digraph.V var1 = Digraph.this.new V(this.index, this.id, this.weight, this.label);
         var1.score = this.score;
         var1.vcscore = this.vcscore;
         return var1;
      }

      @Override
      public String toString() {
         String var1;
         if (Strings.isBlank(this.label)) {
            var1 = Integer.toString(this.id);
         } else {
            var1 = this.label;
         }

         if (this.weight != null) {
            var1 = var1 + Strings.ff("(w=%.3f)", this.weight);
         }

         if (this.vcscore != null) {
            var1 = var1 + Strings.ff("(c=%.3f)", this.vcscore);
         }

         return var1;
      }
   }
}
