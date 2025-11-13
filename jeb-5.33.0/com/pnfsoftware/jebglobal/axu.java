package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.IAddressableUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.PrettyTypeFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.core.util.DecompilerHelper;
import com.pnfsoftware.jeb.util.base.TypedContent;
import java.util.List;

public class axu extends Bv {
   public axu(C var1) {
      super(var1, "AsmContribution");
   }

   @Override
   public boolean isTarget(IUnit var1) {
      return super.isTarget(var1) ? true : DecompilerHelper.getRelatedCodeUnit(var1) == this.getPrimaryTarget();
   }

   @Override
   public TypedContent getItemInformation(IAddressableUnit var1, long var2, String var4, List var5) {
      long var6 = var2 & -72057594037927936L;
      if (var6 == -9079256848778919936L) {
         return null;
      } else {
         String var8 = ((C)this.getPrimaryTarget()).sY(var2);
         if (var8 != null) {
            return this.sY(var8);
         } else {
            if (var6 == -8718968878589280256L) {
               INativeItem var9 = ((C)this.getPrimaryTarget()).getItemObject(var2);
               if (var9 instanceof auu) {
                  return this.sY(var9.getSignature(true));
               }
            }

            if (var1.getItemObject(var2) instanceof INativeType var10) {
               StringBuilder var11 = new StringBuilder();
               var11.append(var10.toString());
               INativeType var12 = TypeUtil.getNonAlias(var10);
               if (var12 instanceof IStructureType) {
                  PrettyTypeFormatter var13 = new PrettyTypeFormatter(2, true);
                  var11.append("\n\n").append(var13.format(var12));
               }

               return this.sY(var11.toString());
            } else {
               return null;
            }
         }
      }
   }
}
