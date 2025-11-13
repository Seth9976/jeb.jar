package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import java.io.InputStream;
import java.util.Map;

public class CudaFatbinIdentifier extends AbstractUnitIdentifier {
   public CudaFatbinIdentifier() {
      super("cuda_fatbin", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("CUDA fatbin"), S.L("fatbin parser and extractor of ELF cubin files containing SASS code"), "PNF Software", Version.create(0, 0, 1)
      );
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      return pC(var1);
   }

   public static boolean pC(IInput var0) {
      if (checkBytes(var0, 0, -1168773808)) {
         try (InputStream var1 = var0.getStream()) {
            if (Sv.pC(var1)) {
               return true;
            }

            return false;
         } catch (Exception var6) {
         }
      }

      return false;
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new bO(var2, var1, var3, var4, this.pdm);
   }
}
