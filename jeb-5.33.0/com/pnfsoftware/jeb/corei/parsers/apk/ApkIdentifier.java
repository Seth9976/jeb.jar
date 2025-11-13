package com.pnfsoftware.jeb.corei.parsers.apk;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeInteger;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypePath;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeSelection;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeString;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.Map;

public class ApkIdentifier extends AbstractUnitIdentifier {
   private static final ILogger pC = GlobalLog.getLogger(ApkIdentifier.class);

   public ApkIdentifier() {
      super("apk", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("APK parser"), S.L("Android APK application handler"), "PNF Software", Version.create(1, 1, 0));
   }

   @Override
   public void initialize(IPropertyDefinitionManager var1) {
      super.initialize(var1);
      this.pdm.addDefinition("ProcessBytecode", PropertyTypeBoolean.create(true), S.L("Process the Dalvik bytecode (classes[N].dex)"));
      this.pdm.addDefinition("MergeMultiDex", PropertyTypeBoolean.create(true), S.L("Merge the DEX files of a multi-DEX APK into a single, unified DEX unit"));
      this.pdm.addDefinition("ProcessManifest", PropertyTypeBoolean.create(true), S.L("Decode and process the APK Manifest"));
      this.pdm
         .addDefinition(
            "ProcessResources",
            PropertyTypeBoolean.create(true),
            S.L("Decode and process the Resources (if this option is true, the Manifest is always processed)")
         );
      this.pdm
         .addDefinition(
            "ArscRestructuringMode",
            PropertyTypeSelection.Builder.create()
               .addEntry(0, S.L("None"), null)
               .addEntry(1, S.L("Basic"), null)
               .addEntry(2, S.L("Move"), null)
               .addEntry(3, S.L("Move and Rename"), null)
               .setDefault(3)
               .build(),
            S.L("Restructuring mode for decoded resource files. This option permits the deobfuscation of obfuscated arsc entries."),
            8
         );
      this.pdm
         .addDefinition(
            "PreferredLocales",
            PropertyTypeString.create(),
            S.L(
               "CSV of preferred locales (in short-form) for selecting APK string resources, used by components such as the auto-commenter, decompiler, etc. Examples: 'fr', 'zh,ko', 'de_CH,de_DE,de'. Leave empty for default (usually, english)."
            )
         );
      this.pdm
         .addDefinition(
            "GenerateAapt2Output",
            PropertyTypeBoolean.create(false),
            S.L("Generate an additional text fragment that will describe the APK resources similarly to AAPT2's output")
         );
      this.pdm.addDefinition("ProcessCertificates", PropertyTypeBoolean.create(true), S.L("Process the APK's certificates data (legacy, v1, v2, v3)"));
      this.pdm
         .addDefinition("ProcessLibraries", PropertyTypeBoolean.create(true), S.L("Process native code (.so) libraries stored in the APK's lib/ directory"));
      this.pdm.addDefinition("ProcessAssets", PropertyTypeBoolean.create(true), S.L("Process unstructured resources stored in the APK's assets/ directory"));
      this.pdm
         .addDefinition(
            "FrameworksDirectory",
            PropertyTypePath.create(),
            S.L(
               "Directory containing additional frameworks. Leave empty to use the default, which is the \".jeb-android-frameworks\" directory in the Home folder"
            ),
            8
         );
      String var2 = "($ANDROID_HOME/docs|$ANDROID_SDK_HOME/docs|$ANDROID_SDK_ROOT/docs)/reference/;https://developer.android.com/reference/;($JAVA_HOME)/docs/api";
      this.pdm
         .addDefinition(
            "AndroidJavadocRoot",
            PropertyTypeString.create(var2),
            S.L(
               "Location of Java and Android API documentation files. By default, local files stored in the Android SDK folder will be preferred over web-based API doc"
            ),
            72
         );
      this.pdm
         .addDefinition(
            "DisableResourcesProcessingThreshold",
            PropertyTypeInteger.createPositiveOrZero(1000),
            S.L("The plugin will suggest disabling the auto-processing of APK resources if the number of resource files exceed that threshold (0 to disable)"),
            2
         );
      this.pdm
         .addDefinition(
            "DisableAssetsProcessingThreshold",
            PropertyTypeInteger.createPositiveOrZero(600),
            S.L("The plugin will suggest disabling the auto-processing of APK assets if the number of asset files exceed that threshold (0 to disable)"),
            2
         );
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (!checkBytes(var1, 0, 80, 75, 3, 4)) {
         return false;
      } else {
         try {
            return new Av(this, var1).kS();
         } catch (IOException var6) {
            pC.error(S.L("APK identification failed (%s)"), var6);
            pC.catchingSilent(var6);
            return false;
         }
      }
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new Ws(var1, var2, var3, var4, this.pdm);
   }
}
