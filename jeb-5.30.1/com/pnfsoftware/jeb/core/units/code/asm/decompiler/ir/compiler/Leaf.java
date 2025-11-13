package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler;

import com.pnfsoftware.jeb.util.format.Strings;
import java.math.BigInteger;

public class Leaf implements INode {
   public static final int FLAG_POSSIBLE_IMM = 1;
   public static final int FLAG_POSSIBLE_VAR = 2;
   public static final int FLAG_POSSIBLE_RANGE = 4;
   public static final int FLAG_POSSIBLE_NON_TERMINAL = 8;
   public static final int FLAG_POSSIBLE_TERMINAL = 7;
   public static final int FLAG_POSSIBLE_ALL = 15;
   static final int FLAG_CONSTANT_IMM = 16;
   static final int FLAG_LASTBIT_IMM = 32;
   public int id;
   public int optionalBitsize;
   public int flags;
   public INodeHandler handler;
   public BigInteger value;

   public Leaf(int var1, int var2, int var3, INodeHandler var4) {
      if ((var3 & 16) != 0) {
         throw new IllegalArgumentException("Invalid flag, wildcard leaf cannot be a determined immediate value");
      } else if (var1 >= 0 && var1 < 1000) {
         this.id = var1;
         this.optionalBitsize = var2;
         this.flags = var3;
         this.handler = var4;
         this.value = null;
      } else {
         throw new IllegalArgumentException("Invalid id for leaf: " + var1);
      }
   }

   public Leaf(int var1, int var2, int var3) {
      this(var1, var2, var3, null);
   }

   public Leaf(long var1, int var3, int var4) {
      if (var4 >= -1 && var4 < 1000) {
         this.id = var4;
         this.optionalBitsize = var3;
         this.flags = 16;
         this.handler = null;
         this.value = BigInteger.valueOf(var1);
      } else {
         throw new IllegalArgumentException("Invalid id for leaf: " + var4);
      }
   }

   public Leaf setHandler(INodeHandler var1) {
      this.handler = var1;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      if (this.value != null) {
         var1.append("I");
      } else {
         var1.append("L");
      }

      if (this.id >= 0) {
         Strings.ff(var1, "(%d)", this.id);
      }

      if (this.value != null) {
         if (this.value.signum() >= 0) {
            Strings.ff(var1, ":0x%s", this.value.toString(16).toUpperCase());
         } else {
            Strings.ff(var1, ":-0x%s", this.value.negate().toString(16).toUpperCase());
         }
      }

      return var1.toString();
   }
}
