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
import java.nio.ByteBuffer;
import java.util.Map;

public class ELFIdentifier extends AbstractUnitIdentifier {
   public static final String TYPE = "elf";

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
      return canIdentify(var1);
   }

   public static boolean canIdentify(IInput var0) {
      return checkBytes(var0, 0, ELF.ElfMagic) && isValidEiClass(readHeaderByte(var0, 4)) && isValidEiData(readHeaderByte(var0, 5));
   }

   private static boolean isValidEiClass(int var0) {
      return var0 == 1 || var0 == 2;
   }

   private static boolean isValidEiData(int var0) {
      return var0 == 1 || var0 == 2;
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new vb(var1, var2, var3, var4, this.pdm);
   }

   public static boolean isELF64(IInput var0) {
      ByteBuffer var1 = var0.getHeader();
      if (var1.limit() < 5) {
         throw new RuntimeException();
      } else if (var1.getInt() != ELF.ElfMagicIntBE) {
         throw new RuntimeException();
      } else {
         byte var2 = var1.get();
         switch (var2) {
            case 1:
               return false;
            case 2:
               return true;
            default:
               throw new RuntimeException("Unsupported ELF class");
         }
      }
   }
}
