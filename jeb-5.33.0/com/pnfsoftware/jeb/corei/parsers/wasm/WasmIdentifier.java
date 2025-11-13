package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.format.Strings;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;

public class WasmIdentifier extends AbstractUnitIdentifier {
   public WasmIdentifier() {
      super("wasm", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("WebAssembly module"), S.L("WebAssembly Module (wasm v1, specs 1.1) binary parser"), "PNF Software", Version.create(1, 2)
      );
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (checkBytes(var1, 0, 0, 97, 115, 109)) {
         ByteBuffer var5 = var1.getHeader();
         if (var5.limit() >= 64) {
            var5.order(ByteOrder.LITTLE_ENDIAN);
            int var6 = var5.getInt(4);
            if (var6 == 1) {
               return true;
            }

            if (var4 != null) {
               var4.put(
                  "FAILURE_REASON", Strings.ff(S.L("The input looks like a WebAssembly binary file, but it reports an unsupported version code: %d"), var6)
               );
            }
         }
      } else if (checkBytes(var1, 0, 40, 109, 111, 100) && var4 != null) {
         var4.put(
            "FAILURE_REASON",
            S.L(
               "The input looks like a WebAssembly S-expression text file (*.wat, *.wast). This plugin processes WebAssembly binaries (*.wasm) only\n.Refer to https://developer.mozilla.org/en-US/docs/WebAssembly/Text_format_to_wasm to learn how to convert WAST text to a WASM module."
            )
         );
      }

      return false;
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new Hv(var1, var2, var3, var4, this.pdm);
   }
}
