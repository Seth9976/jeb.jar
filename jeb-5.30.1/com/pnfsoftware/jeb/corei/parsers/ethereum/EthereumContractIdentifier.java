package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.File;
import java.util.Map;

public class EthereumContractIdentifier extends AbstractUnitIdentifier {
   public static final String TYPE = "eth";
   public static final String PASSTHRU_EXTENSION = ".evm-bytecode";
   public static final String PASSTHRU_EXTENSION2 = ".evm_bytecode";
   public static final String PASSTHRU_EXTENSION3 = ".evmbytecode";
   public static final String EVM_EXTENSION = ".evm";

   public EthereumContractIdentifier() {
      super("eth", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("Ethereum contract parser"), S.L("Ethereum smart contract parser (EVM bytecode)"), "PNF Software", Version.create(0, 0, 1)
      );
   }

   @Override
   public boolean acceptAnyInputBytes() {
      return true;
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (var1 instanceof FileInput) {
         File var5 = ((FileInput)var1).getFile();
         if (Strings.endsWith(var5.getName(), ".evm-bytecode", ".evm_bytecode", ".evmbytecode")) {
            return true;
         }

         if (Strings.endsWith(var5.getName(), ".runtime", ".bin-runtime")) {
            return checkBytes(var1, 0, "60") && checkBytes(var1, 4, "604052");
         }

         if (Strings.endsWith(var5.getName(), ".evm")) {
            return checkBytes(var1, 0, 69, 86, 77);
         }
      } else if (var1 != null) {
         long var7 = var1.getCurrentSize();
         if (var7 >= 43L && checkBytes(var1, (int)(var7 - 2L), 0, 41) && checkBytes(var1, (int)(var7 - 43L), 1, 101)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new PY(var1, var2, var3, var4, this.pdm);
   }
}
