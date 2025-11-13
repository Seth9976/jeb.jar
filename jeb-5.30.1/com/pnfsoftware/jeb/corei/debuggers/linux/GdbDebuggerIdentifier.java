package com.pnfsoftware.jeb.corei.debuggers.linux;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeInteger;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerTargetEnumerator;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerUnitIdentifier;
import com.pnfsoftware.jebglobal.um;
import java.util.Map;

public class GdbDebuggerIdentifier extends AbstractUnitIdentifier implements IDebuggerUnitIdentifier {
   public static final String TYPE = "dbug_elf";
   public static final String propnameBlockingQueryTimeoutSeconds = "BlockingQueryTimeoutSeconds";

   public GdbDebuggerIdentifier() {
      super("dbug_elf", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("GDB/LLDB client debugger unit"), S.L("GDB/LLDB client of a gdbserver or lldb-server instance"), "PNF Software", Version.create(0, 1, 0)
      );
   }

   @Override
   public void initialize(IPropertyDefinitionManager var1) {
      this.createPDM(var1, S.L("Properties of GDB/LLDB debuggers for native code"), 0);
      this.pdm
         .addDefinition(
            "BlockingQueryTimeoutSeconds",
            PropertyTypeInteger.create(0, Integer.MAX_VALUE, 15),
            S.L("Maximum time in seconds to wait for a response from the debugger server")
         );
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      return var2 instanceof INativeCodeUnit;
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      if (!(var4 instanceof IUnit var6)) {
         throw new IllegalArgumentException();
      } else {
         if (var1.isEmpty()) {
            var1 = "debugger";
         }

         um var7 = new um("dbug_elf", var1, var3, var4, this.pdm);
         var7.process();
         var6.addChild(var7, false);
         return var7;
      }
   }

   @Override
   public IDebuggerTargetEnumerator getTargetEnumerator() {
      return null;
   }
}
