package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;

public class PEIdentifier extends AbstractUnitIdentifier {
   public static final String TYPE = "winpe";
   public static final short PortableExecutableMagicLE = 23117;
   public static final short PortableExecutableMagicBE = 19802;

   public PEIdentifier() {
      super("winpe", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("PE loader"), S.L("Windows Portable Executable (PE) code object parser"), "PNF Software", Version.create(0, 0, 1));
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      return canIdentify(var1);
   }

   public static boolean canIdentify(IInput var0) {
      if (!checkBytes(var0, 0, 77, 90)) {
         return false;
      } else {
         ByteBuffer var1 = var0.getHeader();
         if (var1.limit() < 64) {
            return false;
         } else {
            var1.order(ByteOrder.LITTLE_ENDIAN);
            int var2 = var1.getInt(60);
            return var2 >= 0 && var2 + 4 <= var0.getCurrentSize() ? checkBytes(var0, var2, 80, 69, 0, 0) : false;
         }
      }
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new vn(var1, var2, var3, var4, this.pdm);
   }

   public static boolean isPE64(IInput var0) {
      try {
         LEDataInputStream var1 = new LEDataInputStream(var0.getStream());

         boolean var4;
         label45: {
            try {
               if (var1.readShort() != 23117) {
                  throw new RuntimeException();
               }

               var1.skipExact(58L);
               int var2 = var1.readInt();
               if (var2 < 64) {
                  throw new IOException();
               }

               var1.skipExact(var2 - 64);
               if (var1.readInt() != 17744) {
                  throw new RuntimeException();
               }

               var1.skipExact(20L);
               short var3 = var1.readShort();
               switch (var3) {
                  case 267:
                  default:
                     break;
                  case 523:
                     var4 = true;
                     break label45;
               }
            } catch (Throwable var6) {
               try {
                  var1.close();
               } catch (Throwable var5) {
                  var6.addSuppressed(var5);
               }

               throw var6;
            }

            var4 = false;
            var1.close();
            return var4;
         }

         var1.close();
         return var4;
      } catch (IOException var7) {
         throw new RuntimeException(var7);
      }
   }
}
