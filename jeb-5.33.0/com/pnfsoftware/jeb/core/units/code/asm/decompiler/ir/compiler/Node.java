package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler;

public class Node implements INode {
   public O operator;
   public OGroup opgrp;
   public INode[] opnds;
   volatile int descMaxDepth = -1;
   volatile int score = -1;

   public Node(O var1, INode... var2) {
      if (var1 != null && var2 != null) {
         this.operator = var1;
         this.opnds = var2;
      } else {
         throw new NullPointerException();
      }
   }

   public Node(OGroup var1, INode... var2) {
      if (var1 != null && var2 != null) {
         this.opgrp = var1;
         this.opnds = var2;
      } else {
         throw new NullPointerException();
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.operator != null ? this.operator.toString() : this.opgrp.toString());
      var1.append("(");
      int var2 = 0;

      for (INode var6 : this.opnds) {
         if (var2 >= 1) {
            var1.append(", ");
         }

         var1.append(var6.toString());
         var2++;
      }

      var1.append(")");
      return var1.toString();
   }
}
