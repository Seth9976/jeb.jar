package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.util.format.Strings;

public class PrettyTypeFormatter {
   private int levelStructDepth = 1;
   private boolean displayOffsets = false;
   private boolean displayPaddingInfo = false;
   private boolean displayAliasedType = false;
   private boolean displayTrailingSemicolon = false;
   private INativeType type;
   private StringBuilder sb;

   public PrettyTypeFormatter() {
   }

   public PrettyTypeFormatter(int var1, boolean var2) {
      this.setLevelStructDepth(var1);
      this.setDisplayOffsets(var2);
      this.setDisplayPaddingInfo(var2);
      this.setDisplayAliasedType(var2);
   }

   public void setLevelStructDepth(int var1) {
      this.levelStructDepth = var1;
   }

   public int getLevelStructDepth() {
      return this.levelStructDepth;
   }

   public void setDisplayOffsets(boolean var1) {
      this.displayOffsets = var1;
   }

   public boolean isDisplayOffsets() {
      return this.displayOffsets;
   }

   public void setDisplayPaddingInfo(boolean var1) {
      this.displayPaddingInfo = var1;
   }

   public boolean isDisplayPaddingInfo() {
      return this.displayPaddingInfo;
   }

   public void setDisplayAliasedType(boolean var1) {
      this.displayAliasedType = var1;
   }

   public boolean isDisplayAliasedType() {
      return this.displayAliasedType;
   }

   public void setDisplayTrailingSemicolon(boolean var1) {
      this.displayTrailingSemicolon = var1;
   }

   public boolean isDisplayTrailingSemicolon() {
      return this.displayTrailingSemicolon;
   }

   public synchronized String format(INativeType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.type = var1;

         String var2;
         try {
            var2 = this.format();
         } finally {
            this.type = null;
            this.sb = null;
         }

         return var2;
      }
   }

   private String format() {
      this.sb = new StringBuilder();
      this.gen(this.type, this.displayAliasedType, 0, 0);
      if (this.displayTrailingSemicolon) {
         this.sb.append(';');
      }

      if (this.displayOffsets) {
         Strings.ff(this.sb, "  // size:%Xh", this.type.getSize());
      }

      return this.sb.toString();
   }

   private void gen(INativeType var1, boolean var2, int var3, int var4) {
      if (var2 && var1 instanceof IStructureType) {
         var2 = false;
      }

      boolean var5 = false;
      if (var3 < this.levelStructDepth) {
         if (var1 instanceof IArrayType var6) {
            this.gen(var6.getElementType(), var2, var3, var4);
            Strings.ff(this.sb, "[%d]", var6.getElementCount());
         } else if (var1 instanceof IReferenceType var12) {
            this.indentAppend(var12.getMainType().getName(true), var3);
            this.sb.append(Strings.generate('*', var12.getReferenceCount()));
         } else if (var1 instanceof IAliasType var13) {
            this.indentAppend(var13.getName(true), var3);
            if (var2) {
               this.sb.append(" ==> ");
               this.gen(var13.getAliasedType(), var2, var3, var4);
            }
         } else if (var1 instanceof IStructureType var14) {
            int var7 = var14.getPadding();
            int var8 = var14.getAlignment();
            if ((var7 > 1 || var8 > 1) && this.displayPaddingInfo) {
               Strings.ff(this.sb, "/*pack:%s,align:%d*/ ", var7 == Integer.MAX_VALUE ? "NATURAL" : Integer.toString(var7), var8);
            }

            this.indentAppend(var14.isUnion() ? "union" : "struct", var3);
            Strings.ff(this.sb, " %s {\n", var14.getName(true));

            for (IStructureTypeField var10 : var14.getFieldsWithGaps()) {
               INativeType var11 = var10.getType();
               this.gen(var11, false, var3 + 1, var4 + var10.getOffset());
               if (var10.getAlignment() > 1 && this.displayPaddingInfo) {
                  Strings.ff(this.sb, " /*alignas(%d)*/", var10.getAlignment());
               }

               if (var11 != null) {
                  Strings.ff(this.sb, " %s;", var10.getName(true));
               }

               if (this.displayOffsets) {
                  Strings.ff(this.sb, "  // %Xh", var4 + var10.getOffset());
               }

               this.sb.append('\n');
            }

            this.indentAppend("}", var3);
         } else {
            var5 = true;
         }
      } else {
         var5 = true;
      }

      if (var5) {
         if (var1 == null) {
            if (this.displayPaddingInfo) {
               this.indentAppend("/*padding*/", var3);
            }
         } else {
            this.indentAppend(var1.getName(true), var3);
         }
      }
   }

   private void indentAppend(String var1, int var2) {
      this.sb.append(Strings.generate(' ', var2 * 2));
      this.sb.append(var1);
   }
}
