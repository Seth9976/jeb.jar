package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.AbstractEnginesPlugin;
import com.pnfsoftware.jeb.core.BooleanOptionDefinition;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.ListOptionDefinition;
import com.pnfsoftware.jeb.core.OptionDefinition;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class cve extends AbstractEnginesPlugin {
   private static final ILogger q = GlobalLog.getLogger(cve.class);

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation("Test Plugin (debug)", "Internal test engines plugin for JEB", "PNF Software", Version.create(1, 0));
   }

   @Override
   public void execute(IEnginesContext var1, Map var2) {
      Object[] var10000 = new Object[0];
      int var3 = Conversion.stringToInt((String)Maps.get(var2, "spoonsize"));
      String var4 = Strings.safe(Maps.get(var2, "existence"));
      String var5 = Strings.safe(Maps.get(var2, "metal"));
      var10000 = new Object[]{var3, var4, var5};
   }

   @Override
   public List getExecutionOptionDefinitions() {
      return Arrays.asList(
         new OptionDefinition("This is a description string. This is a description string.\nThis is a description string. This is a description string."),
         new OptionDefinition("spoonsize", "Size of the spoon"),
         new BooleanOptionDefinition("existence", false, "Does the spoon really exist?"),
         new ListOptionDefinition("metal", null, "Metal of the spoon", "iron", "copper", "bronze", "silver", "gold")
      );
   }
}
