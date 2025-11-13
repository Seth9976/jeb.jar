package com.pnfsoftware.jeb.util.graph;

import java.util.Comparator;

class Digraph$1 implements Comparator {
   Digraph$1(Digraph var1) {
      this.this$0 = var1;
   }

   public int compare(Integer var1, Integer var2) {
      return -Double.compare(((Digraph.E)this.this$0.edges.get(var1)).ebscore, ((Digraph.E)this.this$0.edges.get(var2)).ebscore);
   }
}
