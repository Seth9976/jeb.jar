package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.IAddressableUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CIdentifierClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstant;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.render.NumberFormatter;
import com.pnfsoftware.jeb.util.base.TypedContent;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.List;

public class adc extends Bv {
   public adc(acu var1) {
      super(var1, "CCodeContribution");
   }

   @Override
   public TypedContent getItemInformation(IAddressableUnit var1, long var2, String var4, List var5) {
      Object var6 = ((acu)this.getPrimaryTarget()).getItemObject(var2);
      StringBuilder var7 = new StringBuilder();
      if (var6 instanceof ICConstant) {
         String var8 = ((ICConstant)var6).getOrigin();
         if (var8 != null) {
            var7.append(S.L("This immediate was not present in the original binary.\n"));
            var7.append(S.L("ORIGIN:")).append(" ").append(var8);
         }
      }

      if (var6 instanceof ICConstantInteger && ((ICConstantInteger)var6).needsCustomFormatting()) {
         NumberFormatter var14 = ((ICConstantInteger)var6).getFormatter();
         if (var14.hasConstantsFormatterOverride()) {
            Object var9 = ((ICConstantInteger)var6).getValue();
            Strings.ff(var7, S.L("Value: %s\n"), var9);
         }
      }

      if (var6 instanceof ICIdentifier) {
         StringBuilder var15 = new StringBuilder();
         ICIdentifier var17 = (ICIdentifier)var6;
         CIdentifierClass var10 = var17.getIdentifierClass();
         Strings.ff(var15, S.L("Category: %s\n"), var10);
         Strings.ff(var15, S.L("Name: %s (original: %s)\n"), var17.getName(), var17.getOriginalName());
         Strings.ff(var15, S.L("Type: %s\n"), var17.getType());
         if (var10 != CIdentifierClass.SYNTHETIC) {
            Long var11 = var17.getAddress();
            if (var10 == CIdentifierClass.LOCAL) {
               var15.append(S.L("Offset:")).append(" ");
            } else {
               var15.append(S.L("Address:")).append(" ");
            }

            if (var11 == null) {
               var15.append("?");
            } else if (var10 == CIdentifierClass.LOCAL) {
               if (var11 < 0L) {
                  Strings.ff(var15, "-0x%X", -var11);
               } else {
                  Strings.ff(var15, "+0x%X", var11);
               }
            } else {
               Strings.ff(var15, "0x%X", var11);
            }

            var15.append("\n");
         }

         var7.append((CharSequence)var15);
      }

      if (var5 != null && !var5.isEmpty()) {
         StringBuilder var16 = new StringBuilder();
         int var18 = 0;

         for (Object var20 : var5) {
            var16.append(Strings.spaces(var18 * 2));
            if (var20 instanceof ICElement var12) {
               var16.append(var12.getElementType());
               String var13 = var12.toString();
               var13 = Formatter.escapeString(Strings.truncateWithSuffix(var13, 80, "..."));
               var16.append(": ").append(var13);
            } else {
               var16.append(var20.toString());
            }

            var16.append("\n");
            var18++;
         }

         if (var7.length() != 0) {
            var7.append("\n\n");
         }

         var7.append(S.L("[AST Objects Stack]\n")).append((CharSequence)var16);
      }

      return var7.length() == 0 ? null : this.pC(var7.toString(), 70);
   }
}
