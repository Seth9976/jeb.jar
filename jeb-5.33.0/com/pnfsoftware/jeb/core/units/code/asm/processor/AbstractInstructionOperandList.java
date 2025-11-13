package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Arrays;

@Ser
public abstract class AbstractInstructionOperandList extends AbstractInstructionOperandGeneric implements IInstructionOperandList {
   @SerId(1)
   private IInstructionOperandGeneric[] operands;
   @SerId(2)
   private int flags;
   public static final int NO_FLAG = 0;
   public static final int SURROUND_BRACKETS = 1;
   public static final int SURROUND_BRACES = 2;
   public static final int SURROUND_PARENTHESES = 4;
   public static final int SURROUND_CHEVRONS = 8;
   public static final int SURROUND_QUOTES = 16;
   public static final int SURROUND_DOUBLE_QUOTES = 32;
   public static final int SURROUND_SPACES = 64;
   public static final int SEPARATOR_SPACE = 256;
   public static final int SEPARATOR_COMMA = 512;
   public static final int SEPARATOR_COLON = 1024;
   public static final int SEPARATOR_SEMI_COLON = 2048;
   public static final int SEPARATOR_HYPHEN = 4096;
   public static final int SEPARATOR_SURROUND_SPACE = 8192;
   public static final int USER_DEFINED_1 = 65536;
   public static final int USER_DEFINED_2 = 131072;
   public static final int USER_DEFINED_3 = 262144;
   public static final int USER_DEFINED_4 = 524288;
   public static final int USER_DEFINED_5 = 1048576;
   public static final int USER_DEFINED_6 = 2097152;
   public static final int USER_DEFINED_7 = 4194304;
   public static final int USER_DEFINED_8 = 8388608;
   public static final int FLAG_MASK = 16777215;

   public AbstractInstructionOperandList(int var1, long var2, int var4, IInstructionOperandGeneric... var5) {
      this(7, var1, var2, var4, var5);
   }

   public AbstractInstructionOperandList(int var1, int var2, long var3, int var5, IInstructionOperandGeneric... var6) {
      super(var1, var2, var3);
      this.operands = var6;
      this.flags = var5;
   }

   @Override
   protected CharSequence formatOperand(IInstruction var1, long var2) {
      if (this.type == 7) {
         StringBuilder var4 = new StringBuilder();

         for (int var5 = 0; var5 < this.operands.length; var5++) {
            if (var5 != 0) {
               var4.append(this.getSeparator());
            }

            IInstructionOperandGeneric var6 = this.operands[var5];
            var4.append(var6.format(var1, var2));
         }

         return var4.toString();
      } else {
         return super.formatOperand(var1, var2);
      }
   }

   @Override
   public String getPrefix(IInstruction var1) {
      StringBuilder var2 = new StringBuilder();
      if ((this.flags & 1) != 0) {
         var2.append('[');
      }

      if ((this.flags & 2) != 0) {
         var2.append('{');
      }

      if ((this.flags & 4) != 0) {
         var2.append('(');
      }

      if ((this.flags & 8) != 0) {
         var2.append('<');
      }

      if ((this.flags & 16) != 0) {
         var2.append('\'');
      }

      if ((this.flags & 32) != 0) {
         var2.append('"');
      }

      if ((this.flags & 64) != 0) {
         var2.append(' ');
      }

      return var2.toString();
   }

   @Override
   public String getSuffix(IInstruction var1) {
      StringBuilder var2 = new StringBuilder();
      if ((this.flags & 64) != 0) {
         var2.append(' ');
      }

      if ((this.flags & 32) != 0) {
         var2.append('"');
      }

      if ((this.flags & 16) != 0) {
         var2.append('\'');
      }

      if ((this.flags & 8) != 0) {
         var2.append('>');
      }

      if ((this.flags & 4) != 0) {
         var2.append(')');
      }

      if ((this.flags & 2) != 0) {
         var2.append('}');
      }

      if ((this.flags & 1) != 0) {
         var2.append(']');
      }

      return var2.toString();
   }

   @Override
   public IInstructionOperand[] getOperands() {
      return this.operands;
   }

   public int getFlags() {
      return this.flags;
   }

   @Override
   public String getSeparator() {
      StringBuilder var1 = new StringBuilder();
      if ((this.flags & 8192) != 0) {
         var1.append(' ');
      }

      if ((this.flags & 512) != 0) {
         var1.append(',');
      }

      if ((this.flags & 1024) != 0) {
         var1.append(':');
      }

      if ((this.flags & 2048) != 0) {
         var1.append(';');
      }

      if ((this.flags & 256) != 0) {
         var1.append(' ');
      }

      if ((this.flags & 4096) != 0) {
         var1.append('-');
      }

      if ((this.flags & 8192) != 0) {
         var1.append(' ');
      }

      return var1.toString();
   }

   @Override
   public IInstructionOperandGeneric merge(long var1) {
      return null;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + this.flags;
      return 31 * var1 + Arrays.hashCode((Object[])this.operands);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!super.equals(var1)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         AbstractInstructionOperandList var2 = (AbstractInstructionOperandList)var1;
         return this.flags != var2.flags ? false : Arrays.equals((Object[])this.operands, (Object[])var2.operands);
      }
   }
}
