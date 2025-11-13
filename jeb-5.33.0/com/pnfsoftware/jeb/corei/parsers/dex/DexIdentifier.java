package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeInteger;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeSelection;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeString;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jebglobal.bfg;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class DexIdentifier extends AbstractUnitIdentifier {
   public DexIdentifier() {
      super("dex", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation("DEX parser", "Android DEX (bytecode) disassembler", "PNF Software", Version.create(1, 0, 0));
   }

   @Override
   public void initialize(IPropertyDefinitionManager var1) {
      super.initialize(var1);
      this.pdm.addDefinition("VerifyVersion", PropertyTypeBoolean.create(false), S.L("Fail parsing if the version number is not in the range [35, 41]"));
      this.pdm.addDefinition("VerifyHashes", PropertyTypeBoolean.create(false), S.L("Fail parsing if the checksum or hash are not as expected"));
      this.pdm
         .addDefinition(
            "VerifyAccessFlags", PropertyTypeBoolean.create(false), S.L("Fail parsing if the access flags of a class, method, or field appear to be illegal")
         );
      this.pdm
         .addDefinition(
            "DalvikParserMode",
            PropertyTypeSelection.Builder.create()
               .addEntry(
                  0,
                  S.L("Legacy"),
                  S.L(
                     "Legacy DEX, including odex and extended opcodes (0xFFxx) - designed for DEX 37-\nHowever, if DEX version 38+ is detected, the mode will auto-adjusted accordingly"
                  )
               )
               .addDefaultEntry(50, "ART", S.L("Android Runtime (ART) introduction"))
               .addEntry(100, "dex 38", S.L("DEX version 38 (+invoke-polymorphic, +invoke-custom, and variants)"))
               .addEntry(110, "dex 39", S.L("DEX version 39 (+const-method-handle, +const-method-type)"))
               .addEntry(120, "dex 40", S.L("DEX version 40 (+names can have spaces)"))
               .addEntry(130, "dex 41", S.L("DEX version 41 (+dex containers)"))
               .addEntry(1000, S.L("Latest"), S.L("Newest, including opcodes and structures that may not be supported in JEB yet"))
               .build(),
            S.L("The parser mode can be either legacy, art, dex[38,41], or latest"),
            8
         );
      this.pdm.addDefinition("ParseExtendedOpcodes", PropertyTypeBoolean.create(true), S.L("Support parsing for the extended opcodes"));
      this.pdm.addDefinition("ParseOptimizedOpcodes", PropertyTypeBoolean.create(true), S.L("Support parsing for the optimized opcodes"));
      this.pdm
         .addDefinition(
            "JarLibraryEnabled",
            PropertyTypeBoolean.create(true),
            S.L(
               "Enable external Java library file parsing. This feature can be used for finer type resolution.\n To activate, enable this property and set either one of JarLibraryClasspath or JarLibraryFolder property."
            )
         );
      this.pdm
         .addDefinition(
            "JarLibraryClasspath",
            PropertyTypeString.create(),
            S.L(
               "A classpath containing Java classfiles to be used along with the dex-defined types. Refer to JarLibraryEnabled.\nIf non-empty, this classpath takes precedence over JarLibraryFolder."
            )
         );
      this.pdm
         .addDefinition(
            "JarLibraryFolder",
            PropertyTypeString.create(),
            Strings.ff(
               S.L(
                  "A folder containing Jar library files to be used along with the dex-defined types. Refer to JarLibraryEnabled.\nThe default value (empty) means to use the folder %s, which always contains a recent copy of the Android framework (android.jar). You may change the value to point to another folder, or drop additional jars in this folder.Note that JarLibraryClasspath takes precedence over this value."
               ),
               "~/.jeb-android-libraries"
            )
         );
      String var2 = "($ANDROID_HOME/docs|$ANDROID_SDK_HOME/docs|$ANDROID_SDK_ROOT/docs)/reference/;https://developer.android.com/reference/;($JAVA_HOME)/docs/api";
      this.pdm
         .addDefinition(
            "AndroidJavadocRoot",
            PropertyTypeString.create(var2),
            S.L(
               "Location of Java/Android API documentation files; by default, local files stored in your Android SDK folder will be preferred over web-based API doc"
            ),
            72
         );
      String var3 = Strings.join(
         ", ",
         Arrays.asList(
            "android",
            "androidx",
            "android_src",
            "butterknife",
            "com.airbnb.epoxy",
            "com.airbnb.lottie",
            "com.android",
            "com.facebook",
            "com.fasterxml",
            "com.firebase",
            "com.google",
            "com.ibm",
            "com.microsoft",
            "dagger",
            "io.reactivex",
            "java",
            "javax",
            "kotlin",
            "kotlinx",
            "mono",
            "net",
            "okhttp3",
            "okio",
            "opentk_*",
            "org",
            "retrofit2",
            "xamarin"
         )
      );
      this.pdm
         .addDefinition(
            "WellKnownLibraryPackages",
            PropertyTypeString.create(var3),
            S.L(
               "CSL of packages that will not originally be fully expanded in code hierarchy tree documents. Use * to prevent auto-expansion for all packages. The star wildcard can also be used as a suffix. In practice, the JEB UI client will not auto-expand those packages by default to avoid cluttering the code hierarchy fragment view."
            ),
            72
         );
      this.pdm
         .addDefinition(
            "ProvideFriendlyCodeNodeLabels",
            PropertyTypeBoolean.create(true),
            S.L(
               "Provide user-friendly code node items (non-qualified, non-internal Java names) to client components.\nMethods and field signatures will be more readable, at the risk of potentially appearing conflicting, esp. on obfuscated samples that heavily reuse names across methods, fields, and types."
            )
         );
      this.pdm
         .addDefinition(
            "ProvideExtraInfoInCodeNodeLabels",
            PropertyTypeBoolean.create(false),
            S.L(
               "Provide additional information in code node items to client components.\nCurrently, when this option is enabled, method labels for concrete non-native methods are appended with a ' /N' string where N is the instruction count of the method."
            )
         );
      this.pdm
         .addDefinition(
            "CallgraphAsyncBuildGracePeriod",
            PropertyTypeInteger.create(10000),
            S.L(
               "The on-demand geenration of the callgraph will be blocking for the provided duration (at most). The rest of the execution will take place asynchronously. Use -1 for a synchronous generation."
            )
         );
      this.pdm
         .addDefinition(
            "CallgraphAsyncRecDetGracePeriod",
            PropertyTypeInteger.create(5000),
            S.L(
               "The on-demand determination of is-recursive for methods will be blocking for the provided duration (at most). The rest of the execution will take place asynchronously. Use -1 for a synchronous generation."
            )
         );
      String var4 = "";

      try {
         byte[] var5 = IO.readInputStream(AssetManager.getAsset("dex-cidb.txt"));
         var4 = Strings.decodeUTF8(var5);
      } catch (IOException var6) {
      }

      this.pdm
         .addDefinition(
            "ContextInfoDb",
            PropertyTypeString.create(var4),
            S.L("Base context information database, holding side-effect information for methods and effective-finality information for fields"),
            7
         );
      this.pdm
         .addDefinition(
            "CIDBMethodCountThresholdNoRegen",
            PropertyTypeInteger.create(-1),
            S.L(
               "Threshold used by the context-information provider to determine whether an addition to the CIDB should trigger an update of the graph of methods and fields. If the dex unit has more than this method count, the provider will not regenerate the CI/CS or SEF determinations. Leave negative to use the default value (~10000)."
            )
         );
      this.pdm
         .addDefinition(
            "CIDBAsyncGenGracePeriod",
            PropertyTypeInteger.create(5000),
            S.L(
               "The on-demand generation of the context-information database will be blocking for the provided duration (at most). The rest of the execution will take place asynchronously. Use -1 for a synchronous generation."
            )
         );
      bfg.pC(this.pdm);
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      return !checkBytes(var1, 0, 100, 101, 120) ? false : checkBytes(var1, 40, 120, 86, 52, 18) || checkBytes(var1, 40, 18, 52, 86, 120);
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new vi(var1, var2, var3, var4, this.pdm);
   }
}
