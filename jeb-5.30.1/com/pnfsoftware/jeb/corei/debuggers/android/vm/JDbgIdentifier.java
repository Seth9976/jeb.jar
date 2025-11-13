package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionGroup;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeInteger;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeString;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerTargetEnumerator;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerUnitIdentifier;
import java.util.Map;

public class JDbgIdentifier extends AbstractUnitIdentifier implements IDebuggerUnitIdentifier {
   static final String TYPE = "dbug_apk";
   public static final String propnameBlockingQueryTimeoutSeconds = "BlockingQueryTimeoutSeconds";
   public static final String propnameAttachPortRangeStart = "AttachPortRangeStart";
   public static final String propnameAttachPortRangeSize = "AttachPortRangeSize";
   public static final String propnameNativeAttachPortRangeStart = "NativeAttachPortRangeStart";
   public static final String propnameNativeAttachPortRangeSize = "NativeAttachPortRangeSize";
   public static final String propnamePreferLldbserver = "PreferLldbserver";
   public static final String propnamePreferredLldbserverVersion = "PreferredLldbserverVersion";
   public static final String propnamePreferredGdbserverVersion = "PreferredGdbserverVersion";
   public static final String propnameAlternateToolNames = "AlternateToolNames";

   public JDbgIdentifier() {
      super("dbug_apk", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("Android Debugger"), S.L("Debugger module for Android applications"), "PNF Software", Version.create(0, 2, 0));
   }

   @Override
   public void initialize(IPropertyDefinitionManager var1) {
      this.createPDM(var1, S.L("Properties of the Android debuggers"), 0);
      IPropertyDefinitionGroup var2 = this.pdm.addGroup(S.L("Bytecode debugger"));
      IPropertyDefinitionGroup var3 = this.pdm.addGroup(S.L("Native debugger"));
      this.pdm
         .addDefinition(
            "BlockingQueryTimeoutSeconds",
            PropertyTypeInteger.create(0, Integer.MAX_VALUE, 15),
            S.L("Maximum time in seconds to wait for a response from the debugger server")
         );
      var2.addDefinition(
         "AttachPortRangeStart",
         PropertyTypeInteger.create(1024, 65535, 8900),
         S.L("Preferred TCP port to use and listen to when attaching to the JDWP debugger")
      );
      var2.addDefinition(
         "AttachPortRangeSize",
         PropertyTypeInteger.create(1, 100, 20),
         S.L("Number of ports to try after the preferred port if the preferred port is in useby another program"),
         32
      );
      var3.addDefinition(
         "NativeAttachPortRangeStart",
         PropertyTypeInteger.create(1024, 65535, 8950),
         S.L("Preferred TCP port to use and listen to when attaching to the native debugger")
      );
      var3.addDefinition(
         "NativeAttachPortRangeSize",
         PropertyTypeInteger.create(1, 100, 20),
         S.L("Number of ports to try after the preferred port if the preferred port is in useby another program"),
         32
      );
      var3.addDefinition(
         "PreferLldbserver", PropertyTypeBoolean.create(true), S.L("Prefer the use of LLDB server over GDB server for debugging native Android code.")
      );
      var3.addDefinition(
         "PreferredLldbserverVersion",
         PropertyTypeString.create(),
         S.L(
            "Preferred LLDB server version to use. JEB does not ship with all versions of LLDB server for all platforms. Currently supported: '3.1', '17.0.2' (from NDK 26.1), '18.0.1' (from NDK 27.0), '19.0.0' (from NDK 28.0). Leave blank to use the default (lldb-version-19.0.0)."
         ),
         40
      );
      var3.addDefinition(
         "PreferredGdbserverVersion",
         PropertyTypeString.create(),
         S.L(
            "Preferred GDB server version to use. JEB does not ship with all versions of GDB server for all platforms. Currently supported: '7.7' (x86, mips) '7.11' (arm), '8.3' (x86, arm). Leave blank to use the default."
         ),
         40
      );
      var3.addDefinition(
         "AlternateToolNames",
         PropertyTypeString.create(),
         S.L(
            "CSV list of preferred (alternate) tool names. Currently, the only alternate supported is for `su`. Example: set this property to 'su=foo' to let JEB execute `foo` instead of `su` when root commands are attempted."
         ),
         8
      );
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      return !(var2 instanceof com.pnfsoftware.jeb.corei.parsers.apk.ej) ? false : var1 == null;
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      if (!(var4 instanceof IUnit var6)) {
         throw new IllegalArgumentException();
      } else {
         if (var1.isEmpty()) {
            var1 = "VM";
         }

         CI var7 = new CI(var1, var3, var4, this.pdm);
         var7.process();
         var6.addChild(var7, false);
         return var7;
      }
   }

   @Override
   public IDebuggerTargetEnumerator getTargetEnumerator() {
      return new Uz();
   }
}
