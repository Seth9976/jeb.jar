package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.coordinates.ClassCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.FieldCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.PackageCoordinates;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;

public class bhf {
   private com.pnfsoftware.jeb.corei.parsers.dex.vi pC;

   public bhf(com.pnfsoftware.jeb.corei.parsers.dex.vi var1) {
      this.pC = var1;
   }

   public ICodeCoordinates pC(String var1) {
      if (var1 == null) {
         return null;
      } else if (!var1.contains("->")) {
         if (!var1.contains(";")) {
            com.pnfsoftware.jeb.corei.parsers.dex.qt var8 = this.pC.ld().wS(var1);
            return var8 == null ? null : new PackageCoordinates(var8.getIndex());
         } else {
            bfs var7 = this.pC.WR().pC(var1);
            return var7 == null ? null : new ClassCoordinates(var7.getIndex());
         }
      } else if (var1.contains(":")) {
         bft var6 = this.pC.oT().pC(var1);
         return var6 != null && var6.isInternal() ? new FieldCoordinates(var6.getIndex()) : null;
      } else if (var1.contains("(")) {
         int var2 = -1;
         int var3 = var1.indexOf(43);
         boolean var4 = false;
         if (var3 >= 0) {
            String var5 = var1.substring(var3 + 1);
            if (var5.toUpperCase().equals("END")) {
               var4 = true;
            } else {
               var2 = Conversion.stringToInt(var5, -1);
            }

            var1 = var1.substring(0, var3);
         }

         if (var1.endsWith("#end")) {
            var4 = true;
            var1 = var1.substring(0, var1.length() - "#end".length());
         }

         bfu var9 = this.pC.fI().pC(var1);
         if (var9 != null && var9.isInternal()) {
            return (ICodeCoordinates)(var2 < 0 ? new MethodCoordinates(var9.getIndex(), var4) : new InstructionCoordinates(var9.getIndex(), var2));
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   public String pC(ICodeCoordinates var1) {
      return this.pC(var1, true, true);
   }

   public String pC(ICodeCoordinates var1, boolean var2, boolean var3) {
      if (var1 instanceof ClassCoordinates) {
         int var10 = ((ClassCoordinates)var1).getClassId();
         bfs var14 = this.pC.ys(var10);
         return var14 == null ? null : var14.getSignature(var2, var3);
      } else if (var1 instanceof FieldCoordinates) {
         int var9 = ((FieldCoordinates)var1).getFieldId();
         bft var13 = this.pC.E(var9);
         return var13 == null ? null : var13.getSignature(var2, var3);
      } else if (var1 instanceof MethodCoordinates) {
         int var8 = ((MethodCoordinates)var1).getMethodId();
         bfu var12 = this.pC.sY(var8);
         if (var12 == null) {
            return null;
         } else {
            return ((MethodCoordinates)var1).isEndFlag() ? var12.getSignature(var2, var3) + "+END" : var12.getSignature(var2, var3);
         }
      } else if (var1 instanceof InstructionCoordinates) {
         int var7 = ((InstructionCoordinates)var1).getMethodId();
         bfu var11 = this.pC.sY(var7);
         if (var11 == null) {
            return null;
         } else {
            int var6 = ((InstructionCoordinates)var1).getOffset();
            return var6 < 0 ? null : Strings.ff("%s+%Xh", var11.getSignature(var2, var3), var6);
         }
      } else if (var1 instanceof PackageCoordinates) {
         int var4 = ((PackageCoordinates)var1).getPackageId();
         com.pnfsoftware.jeb.corei.parsers.dex.qt var5 = this.pC.oT(var4);
         return var5 == null ? null : var5.getSignature(var2, var3);
      } else {
         return null;
      }
   }

   public String pC(String var1, boolean var2) {
      ICodeCoordinates var3 = this.pC(var1);
      return var3 == null ? null : this.pC(var3, var2, true);
   }
}
