package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.coordinates.ClassCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.FieldCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.PackageCoordinates;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;

public class blb {
   public static final String q = "#end";
   public static final String RF = "END";
   private com.pnfsoftware.jeb.corei.parsers.dex.bK xK;

   public blb(com.pnfsoftware.jeb.corei.parsers.dex.bK var1) {
      this.xK = var1;
   }

   public ICodeCoordinates q(String var1) {
      if (var1 == null) {
         return null;
      } else if (!var1.contains("->")) {
         if (!var1.contains(";")) {
            com.pnfsoftware.jeb.corei.parsers.dex.Vj var8 = this.xK.io().Dw(var1);
            return var8 == null ? null : new PackageCoordinates(var8.getIndex());
         } else {
            bjn var7 = this.xK.oQ().q(var1);
            return var7 == null ? null : new ClassCoordinates(var7.getIndex());
         }
      } else if (var1.contains(":")) {
         bjo var6 = this.xK.Me().q(var1);
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

         bjp var9 = this.xK.PV().q(var1);
         if (var9 != null && var9.isInternal()) {
            return (ICodeCoordinates)(var2 < 0 ? new MethodCoordinates(var9.getIndex(), var4) : new InstructionCoordinates(var9.getIndex(), var2));
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   public String q(ICodeCoordinates var1) {
      return this.q(var1, true, true);
   }

   public String q(ICodeCoordinates var1, boolean var2, boolean var3) {
      if (var1 instanceof ClassCoordinates) {
         int var10 = ((ClassCoordinates)var1).getClassId();
         bjn var14 = this.xK.nf(var10);
         return var14 == null ? null : var14.getSignature(var2, var3);
      } else if (var1 instanceof FieldCoordinates) {
         int var9 = ((FieldCoordinates)var1).getFieldId();
         bjo var13 = this.xK.oW(var9);
         return var13 == null ? null : var13.getSignature(var2, var3);
      } else if (var1 instanceof MethodCoordinates) {
         int var8 = ((MethodCoordinates)var1).getMethodId();
         bjp var12 = this.xK.gO(var8);
         if (var12 == null) {
            return null;
         } else {
            return ((MethodCoordinates)var1).isEndFlag() ? var12.getSignature(var2, var3) + "+END" : var12.getSignature(var2, var3);
         }
      } else if (var1 instanceof InstructionCoordinates) {
         int var7 = ((InstructionCoordinates)var1).getMethodId();
         bjp var11 = this.xK.gO(var7);
         if (var11 == null) {
            return null;
         } else {
            int var6 = ((InstructionCoordinates)var1).getOffset();
            return var6 < 0 ? null : Strings.ff("%s+%Xh", var11.getSignature(var2, var3), var6);
         }
      } else if (var1 instanceof PackageCoordinates) {
         int var4 = ((PackageCoordinates)var1).getPackageId();
         com.pnfsoftware.jeb.corei.parsers.dex.Vj var5 = this.xK.lm(var4);
         return var5 == null ? null : var5.getSignature(var2, var3);
      } else {
         return null;
      }
   }

   public String q(String var1, boolean var2) {
      ICodeCoordinates var3 = this.q(var1);
      return var3 == null ? null : this.q(var3, var2, true);
   }

   public String RF(String var1) {
      return this.q(var1, false);
   }
}
