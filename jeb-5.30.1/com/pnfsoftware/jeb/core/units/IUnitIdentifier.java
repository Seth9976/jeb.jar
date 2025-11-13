package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IInput;
import java.util.Map;

public interface IUnitIdentifier extends IUnitPlugin {
   String KEY_FAILURE_REASON = "FAILURE_REASON";

   boolean acceptAnyInputBytes();

   default boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      return this.canIdentify(var1, var2);
   }

   default boolean canIdentify(IInput var1, IUnitCreator var2) {
      return false;
   }

   default IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return this.prepare(var1, var2, var3, var4);
   }

   default IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4) {
      return null;
   }
}
