package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler;

public class Util {
   public static Node N(O var0, INode... var1) {
      return new Node(var0, var1);
   }

   public static Node N(OGroup var0, INode... var1) {
      return new Node(var0, var1);
   }

   public static Leaf L(int var0, int var1, int var2, INodeHandler var3) {
      return new Leaf(var0, var1, var2, var3);
   }

   public static Leaf L(int var0) {
      return new Leaf(var0, 0, 15);
   }

   public static Leaf L(int var0, int var1) {
      return new Leaf(var0, var1, 15);
   }

   public static Leaf LT(int var0) {
      return new Leaf(var0, 0, 7);
   }

   public static Leaf LI(int var0) {
      return new Leaf(var0, 0, 1);
   }

   public static Leaf LV(int var0) {
      return new Leaf(var0, 0, 2);
   }

   public static Leaf LIV(int var0) {
      return new Leaf(var0, 0, 3);
   }

   public static Leaf LC(long var0, int var2, int var3) {
      return new Leaf(var0, var2, var3);
   }

   public static Leaf LC(long var0, int var2) {
      return new Leaf(var0, var2, -1);
   }

   public static Leaf LC(long var0) {
      return new Leaf(var0, 0, -1);
   }

   public static SubstitutionDefinition SD(INode var0, INode var1) {
      return new SubstitutionDefinition(var0, var1);
   }

   public static void setMaxDepths(INode var0) {
      if (var0 instanceof Node && ((Node)var0).descMaxDepth < 0) {
         calcMaxDepths((Node)var0);
      }
   }

   public static int calcMaxDepths(Node var0) {
      int var1 = 0;

      for (INode var5 : var0.opnds) {
         if (var5 instanceof Node) {
            int var6 = calcMaxDepths((Node)var5);
            if (var6 > var1) {
               var1 = var6;
            }
         }
      }

      boolean var7 = var0.operator == O.NEG;
      int var8 = (var7 ? 0 : 1) + var1;
      var0.descMaxDepth = var8;
      return var8;
   }
}
