package com.pnfsoftware.jeb.corei.parsers.machofat;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import java.nio.ByteBuffer;
import java.util.Map;

public class MachOFatIdentifier extends AbstractUnitIdentifier {
   public MachOFatIdentifier() {
      super("machofat", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("Mach-O fat files loader"), S.L("Apple Mach-O fat files parser"), "PNF Software", Version.create(0, 0, 1));
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (var1 == null) {
         return false;
      } else {
         ByteBuffer var5 = var1.getHeader();
         if (var5.limit() <= 8) {
            return false;
         } else {
            int var6 = var5.getInt();
            if (var6 == -889275713 || var6 == -1078264118) {
               throw new RuntimeException("TBI: 64b Mach-O FAT");
            } else if (var6 != -889275714 && var6 != -1095041334) {
               return false;
            } else {
               short var7 = var5.getShort();
               if (var7 != 0) {
                  return false;
               } else {
                  short var8 = var5.getShort();
                  return var8 < 43;
               }
            }
         }
      }
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new K(var1, var2, var3, var4, this.pdm);
   }
}
