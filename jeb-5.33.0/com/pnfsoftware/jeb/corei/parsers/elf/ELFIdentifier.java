package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.codeobject.ELF;
import java.util.Map;

public class ELFIdentifier extends AbstractUnitIdentifier {
   public ELFIdentifier() {
      super("elf", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("ELF loader"), S.L("Linux Executable and Linkable Format (ELF) code object parser"), "PNF Software", Version.create(0, 0, 1)
      );
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      return pC(var1);
   }

   public static boolean pC(IInput var0) {
      return checkBytes(var0, 0, ELF.ElfMagic) && pC(readHeaderByte(var0, 4)) && A(readHeaderByte(var0, 5));
   }

   private static boolean pC(int var0) {
      return var0 == 1 || var0 == 2;
   }

   private static boolean A(int var0) {
      return var0 == 1 || var0 == 2;
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new sy(var1, var2, var3, var4, this.pdm);
   }
}
