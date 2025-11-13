package com.pnfsoftware.jeb.corei.parsers.classfile;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeInteger;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Map;

public class JavaClassfileIdentifier extends AbstractUnitIdentifier {
   private static final ILogger pC = GlobalLog.getLogger(JavaClassfileIdentifier.class);

   public JavaClassfileIdentifier() {
      super("javaclass", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("Java Classfile parser"), S.L("Java Classfile handler"), "PNF Software", Version.create(0, 0, 1));
   }

   @Override
   public void initialize(IPropertyDefinitionManager var1) {
      super.initialize(var1);
      this.pdm
         .addDefinition(
            "UseD8ForDexConversion",
            PropertyTypeBoolean.create(true),
            S.L("Prefer the d8/r8 compiler over the legacy dx compiler to perform Java bytecode to Dalvik bytecode transpilation"),
            2
         );
      this.pdm
         .addDefinition(
            "MinimumAPILevel",
            PropertyTypeInteger.create(1, 100, 29),
            S.L(
               "If d8 is the selected converter, you may specify the minimal API level provided to d8 when performing the conversion. A higher minimal API level may be necessary to translate some Java constructs to dex; however, a potential drawback is that d8 will apply optimizations and remove unused code paths based on the provided level."
            )
         );
      this.pdm
         .addDefinition(
            "EnableDesugaring",
            PropertyTypeBoolean.create(true),
            S.L(
               "If d8 is the selected converter, enable this option to desugar Java 8+ high-level constructs (such as lambdas) into  pseudo-equivalent lower-level constructs. It is recommended to keep this option enabled for large Jar files, since d8 may have problems regenerating call sites and method handles."
            )
         );
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      return checkBytes(var1, 0, 202, 254, 186, 190);
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new Ws(var1, var2, var3, var4, this.pdm);
   }
}
