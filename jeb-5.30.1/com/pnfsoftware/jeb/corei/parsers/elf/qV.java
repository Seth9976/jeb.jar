package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.output.tree.INode;
import com.pnfsoftware.jeb.core.output.tree.INodeCoordinates;
import com.pnfsoftware.jeb.core.output.tree.impl.AbstractTreeDocument;
import com.pnfsoftware.jeb.core.output.tree.impl.NodeCoordinates;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.Dwarf;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDIE;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDIEAttribute;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDwCompileUnit;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import com.pnfsoftware.jebglobal.ciw;
import com.pnfsoftware.jebglobal.cix;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SerDisabled
public class qV extends AbstractTreeDocument {
   private final IELFUnit q;
   private final INativeCodeUnit RF;
   private List xK;

   public qV(IELFUnit var1) {
      this.q = var1;
      this.RF = (INativeCodeUnit)UnitUtil.findFirstChildByType(var1, INativeCodeUnit.class, false);
      List var2 = var1.getDwarfDIEs();
      if (var2 != null && !var2.isEmpty()) {
         this.xK = new ArrayList();

         for (IDIE var4 : var2) {
            this.xK.add(new cix(var4));
         }
      }
   }

   @Override
   public IUnit getUnit() {
      return this.q;
   }

   @Override
   public List getColumnLabels() {
      return Arrays.asList(S.L("Offset"), S.L("Name"), S.L("Value"), S.L("Raw Value"));
   }

   @Override
   public List getRoots() {
      return this.xK;
   }

   @Override
   public String coordinatesToAddress(INodeCoordinates var1) {
      return this.coordinatesToAddress(var1, var1x -> {
         INode var2 = (INode)var1x.get(var1x.size() - 1);
         if (var2 instanceof cix) {
            return q(((cix)var2).q());
         } else {
            return var2 instanceof ciw ? q(((cix)var1x.get(var1x.size() - 2)).q()) + "+" + var1.getPath().get(var1.getPath().size() - 1) : null;
         }
      });
   }

   private static String q(IDIE var0) {
      IDwCompileUnit var1 = var0.getCompileUnit();
      return Long.toHexString(var1.getOffset()) + "h+" + Long.toHexString(var0.getOffset()) + "h";
   }

   @Override
   public INodeCoordinates addressToCoordinates(String var1) {
      if (var1 != null && !var1.startsWith("sub_")) {
         String[] var2 = var1.split("\\+");
         IDIE var3 = null;
         int var4 = -1;
         long var5 = Conversion.stringToLong(var2[0]);
         IDIE var7 = null;

         for (int var8 = 0; var8 < this.xK.size(); var8++) {
            if (((cix)this.xK.get(var8)).q().getOffset() == var5) {
               var7 = ((cix)this.xK.get(var8)).q();
               var4 = var8;
               break;
            }
         }

         for (int var12 = 0; var12 < this.xK.size(); var12++) {
            var3 = var7.getChildAtOffset(Conversion.stringToLong(var2[1]));
            if (var3 != null) {
               break;
            }
         }

         if (var3 == null) {
            return null;
         } else {
            ArrayList var13 = new ArrayList();

            while (var3.getParent() != null) {
               IDIE var9 = var3.getParent();
               int var10 = -1;

               for (int var11 = 0; var11 < var9.getChildren().size(); var11++) {
                  if (var9.getChildren().get(var11) == var3) {
                     var10 = var11;
                     break;
                  }
               }

               if (var10 < 0) {
                  return null;
               }

               var13.add(0, var9.getAttributes().size() + var10);
               var3 = var9;
            }

            var13.add(0, var4);
            if (var2.length > 2) {
               var13.add(Conversion.stringToInt(var2[2]));
            }

            return new NodeCoordinates(var13);
         }
      } else {
         return null;
      }
   }

   @Override
   public String getReferencedAddress(INode var1) {
      if (var1 instanceof ciw) {
         IDIEAttribute var2 = ((ciw)var1).q();
         if (var2.getType() == Dwarf.DwarfFormType.reference) {
            IDIE var5 = var2.getReference();
            return q(var5);
         }

         if (var2.getType() == Dwarf.DwarfFormType.address) {
            long var3 = this.RF.getVirtualImageBase() - this.q.getLoaderInformation().getImageBase() + (Long)var2.getForm();
            return "sub_" + Long.toHexString(var3);
         }
      }

      return null;
   }
}
