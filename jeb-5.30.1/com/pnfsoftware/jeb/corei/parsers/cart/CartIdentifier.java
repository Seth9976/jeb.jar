package com.pnfsoftware.jeb.corei.parsers.cart;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Map;

public class CartIdentifier extends AbstractUnitIdentifier {
   private static final ILogger logger = GlobalLog.getLogger(CartIdentifier.class);
   public static final String TYPE = "cart";

   public CartIdentifier() {
      super("cart", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("CART archive parser"), S.L("Compressed and RC4 Transport parser for malware transport"), "PNF Software", Version.create(1, 0, 0)
      );
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (var1 == null) {
         return false;
      } else if (var1.getCurrentSize() < 56L) {
         return false;
      } else {
         return !checkBytes(var1, 0, 67, 65, 82, 84) ? false : checkBytes(var1, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0);
      }
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new eo(var1, var2, var3, var4, this.pdm);
   }
}
