package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.core.output.CoordinatesConversionPrecision;
import com.pnfsoftware.jeb.core.output.code.CodeDocument;
import com.pnfsoftware.jeb.core.output.code.CodeLine;
import com.pnfsoftware.jeb.core.output.code.coordinates.CodeCoordinatesUtil;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.output.text.ICoordinates;
import com.pnfsoftware.jeb.core.output.text.impl.Coordinates;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionGroup;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeInteger;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeSelection;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class JavaDocument extends CodeDocument {
   private static final ILogger logger = GlobalLog.getLogger(JavaDocument.class);
   public static final String propnameSortItemsForRendering = "SortItemsForRendering";
   public static final String propnameUseDebugInfoNames = "UseDebugInfoNames";
   public static final String propnameDisplayMethodInternalsAsComment = "DisplayMethodInternalsAsComment";
   public static final String propnameDisplayPrivateMethodsLast = "DisplayPrivateMethodsLast";
   public static final String propnameInsertBlankLinesAfterCompounds = "InsertBlankLinesAfterCompounds";
   public static final String propnameGenerateSyntheticFields = "GenerateSyntheticFields";
   public static final String propnameGenerateSyntheticMethods = "GenerateSyntheticMethods";
   public static final String propnameGenerateAnnotations = "GenerateAnnotations";
   public static final String propnameResolveMethodCallTargets = "ResolveMethodCallTargets";
   public static final String propnameResolveFieldAccessTargets = "ResolveFieldAccessTargets";
   public static final String propnameGenerateOverrideAnnotations = "GenerateOverrideAnnotations";
   public static final String propnameGenerateLambdas = "GenerateLambdas";
   public static final String propnameDisplayASTElementStackOnHover = "DisplayASTElementStackOnHover";
   public static final String propnameSplitCallArgThreshold = "SplitCallArgThreshold";
   public static final String propnameDoNotGenerateThisIfPossible = "DoNotGenerateThisIfPossible";
   public static final String propnameDisregardCollapse = "DisregardCollapse";
   private boolean disposed;
   private IJavaSourceUnit ast;
   private IPropertyManager pm;
   private IDexDecompilerUnit dexdec;
   private IEventListener unitListener;
   private IEventListener pmListener;
   private JavaOutputSink singlePart;
   int optionSortItemsForRendering;
   boolean optionUseDebugInfoNames;
   boolean optionDisplayPrivateMethodsLast;
   boolean optionInsertBlankLinesAfterCompounds;
   boolean optionGenerateSyntheticFields;
   boolean optionGenerateSyntheticMethods;
   boolean optionGenerateSyntheticTypes;
   boolean optionGenerateAnnotations;
   int optionDisplayMethodInternalsAsComment;
   boolean optionResolveMethodCallTargets;
   boolean optionResolveFieldAccessTargets;
   boolean optionGenerateOverrideAnnotations;
   boolean optionGenerateLambdas;
   int optionSplitCallArgThreshold;
   boolean optionDoNotGenerateThisIfPossible;
   boolean optionDisregardCollapse;

   public static void buildPDM(IPropertyDefinitionManager var0) {
      IPropertyDefinitionGroup var1 = var0.addGroup("text");
      var1.addDefinition("UseDebugInfoNames", PropertyTypeBoolean.create(true), S.L("Use names located in dex debug information (if available)"));
      var1.addDefinition(
         "DoNotGenerateThisIfPossible",
         PropertyTypeBoolean.create(false),
         S.L("When no naming conflict is detected, avoid generating the 'this' prefix when accessing objects' attributes or invoking objects' methods")
      );
      var1.addDefinition(
         "SortItemsForRendering",
         PropertyTypeSelection.Builder.create()
            .addEntry(0, S.L("None"), S.L("No sort (rendered as they appear in native code)"))
            .addDefaultEntry(1, S.L("Alpha, Access"), S.L("Items sorted alphabetically and by access type"))
            .build(),
         S.L("Sort class, field and method source items before rendering them")
      );
      var1.addDefinition(
         "DisplayMethodInternalsAsComment",
         PropertyTypeSelection.Builder.create()
            .addDefaultEntry(0, S.L("None"), S.L("Display no addition"))
            .addEntry(1, S.L("Signature"), S.L("Display the original method signature"))
            .addEntry(2, S.L("Signature, AST"), S.L("Display the original method signature and the flattened AST of the method"))
            .build(),
         S.L("When rendering source, prepend method internals as a comment block before rendering the method")
      );
      var1.addDefinition(
         "DisplayPrivateMethodsLast", PropertyTypeBoolean.create(false), S.L("Output private methods last (after public, default and protected methods)")
      );
      var1.addDefinition("InsertBlankLinesAfterCompounds", PropertyTypeBoolean.create(true), S.L("Insert blank lines between compounds"));
      var1.addDefinition("GenerateSyntheticFields", PropertyTypeBoolean.create(false), S.L("Explicitly generate synthetic fields"));
      var1.addDefinition("GenerateSyntheticMethods", PropertyTypeBoolean.create(false), S.L("Explicitly generate synthetic methods"));
      var1.addDefinition("GenerateAnnotations", PropertyTypeBoolean.create(true), S.L("Generate Java annotations"));
      var1.addDefinition(
         "ResolveMethodCallTargets",
         PropertyTypeBoolean.create(true),
         S.L("Resolve calls to virtual and static methods, possibly offering a list of candidates methods to client code")
      );
      var1.addDefinition("ResolveFieldAccessTargets", PropertyTypeBoolean.create(true), S.L("Resolve accesses to object and class fields"));
      var1.addDefinition(
         "GenerateOverrideAnnotations",
         PropertyTypeBoolean.create(true),
         S.L("Geneate additional @Override annotations for methods for which super implementations or definitions were found")
      );
      var1.addDefinition(
         "GenerateLambdas",
         PropertyTypeBoolean.create(true),
         S.L("Favor the generation of lambda functions (Java 8+) if possible. Lambdas must have been reconstructed (engines option)")
      );
      var1.addDefinition(
         "DisplayASTElementStackOnHover",
         PropertyTypeBoolean.create(true),
         S.L("Display the parent and ancestors of a decompiled Java AST node when the mouse cursor hovers over it (useful to debug or troubleshoot a script)")
      );
      var1.addDefinition(
         "SplitCallArgThreshold",
         PropertyTypeInteger.create(10),
         S.L(
            "Split a method invocation's arguments on single-lines, with parameter details, for methods with more than that number of parameters (does not apply to lambdas). Use 0 to disable splitting."
         )
      );
      var1.addDefinition(
         "DisregardCollapse",
         PropertyTypeBoolean.create(),
         S.L("Disregard the 'collapse' attribute set on dex items, i.e. always generate fully-expanded class or method code.")
      );
   }

   public JavaDocument(IJavaSourceUnit var1) {
      this.ast = var1;
      this.dexdec = (IDexDecompilerUnit)var1.getParent();
      this.setupOptions(false);
      var1.addListener(this.unitListener = new JavaDocument$1(this));
      this.pm = this.dexdec.getPropertyManager();
      this.pm.addListener(this.pmListener = new JavaDocument$2(this));
   }

   void setupOptions(boolean var1) {
      IPropertyManager var2 = this.dexdec.getPropertyManager();
      this.optionSortItemsForRendering = var2.getInteger("SortItemsForRendering");
      this.optionUseDebugInfoNames = var2.getBoolean("UseDebugInfoNames");
      this.optionDisplayPrivateMethodsLast = var2.getBoolean("DisplayPrivateMethodsLast");
      this.optionInsertBlankLinesAfterCompounds = var2.getBoolean("InsertBlankLinesAfterCompounds");
      this.optionGenerateSyntheticFields = var2.getBoolean("GenerateSyntheticFields");
      this.optionGenerateSyntheticMethods = var2.getBoolean("GenerateSyntheticMethods");
      this.optionGenerateAnnotations = var2.getBoolean("GenerateAnnotations");
      this.optionDisplayMethodInternalsAsComment = var2.getInteger("DisplayMethodInternalsAsComment");
      this.optionResolveMethodCallTargets = var2.getBoolean("ResolveMethodCallTargets");
      this.optionResolveFieldAccessTargets = var2.getBoolean("ResolveFieldAccessTargets");
      this.optionGenerateOverrideAnnotations = var2.getBoolean("GenerateOverrideAnnotations");
      this.optionGenerateLambdas = var2.getBoolean("GenerateLambdas");
      this.optionSplitCallArgThreshold = var2.getInteger("SplitCallArgThreshold");
      this.optionDoNotGenerateThisIfPossible = var2.getBoolean("DoNotGenerateThisIfPossible");
      this.optionDisregardCollapse = var2.getBoolean("DisregardCollapse");
      if (var1) {
         this.notifyListeners(new JebEvent(J.UnitChange));
      }
   }

   public IJavaSourceUnit getOwnerUnit() {
      return this.ast;
   }

   @Override
   public void dispose() {
      if (!this.disposed) {
         super.dispose();
         this.ast.removeListener(this.unitListener);
         this.pm.removeListener(this.pmListener);
         this.disposed = true;
      }
   }

   @Override
   public IUnit getUnit() {
      return this.ast;
   }

   @Override
   public long getAnchorCount() {
      return 1L;
   }

   public JavaOutputSink getDocumentPart(long var1, int var3, int var4) {
      JavaOutputSink var5 = new JavaOutputSink(0, this, this.dexdec);
      var5.registerAnchor("single_anchor");
      IJavaElement var6 = this.ast.getASTElement();
      var5.setDynamicContentManager(this.dexdec.getDynamicContentManager());
      var6.generate(var5);

      try {
         var5.validate();
      } catch (Exception var13) {
      }

      this.singlePart = var5;
      Map var7 = var5.getIdentifierCoordinates();

      for (long var9 : var7.keySet()) {
         for (ICodeCoordinates var12 : (List)var7.get(var9)) {
            this.ast.recordIdentifierPositions(var9, var12);
         }
      }

      return var5;
   }

   @Override
   public String coordinatesToAddress(ICoordinates var1, AddressConversionPrecision var2) {
      ICodeCoordinates var3 = this.coordinatesToCodeCoordinates(var1);
      if (var3 == null) {
         return null;
      } else {
         IDexUnit var4 = (IDexUnit)((IDexDecompilerUnit)this.ast.getParent()).getParent();
         return var4.getAddressFromCodeCoordinates(var3);
      }
   }

   public ICodeCoordinates coordinatesToCodeCoordinates(ICoordinates var1) {
      if (var1 == null) {
         return null;
      } else {
         int var2 = var1.getLineDelta();
         if (this.singlePart != null && var2 >= 0 && var2 < this.singlePart.getCountOfLines()) {
            List var3 = this.singlePart.getLines();
            CodeLine var4 = (CodeLine)var3.get(var2);
            ICodeCoordinates var5 = var4.getCoordinates(var1.getColumnOffset());
            ICodeCoordinates var6 = var4.getLineCoordinates();
            if (var5 == null && var6 == null) {
               int var7 = var2 + 1;

               for (int var8 = Math.min(var7 + 10, var3.size()); var7 < var8; var7++) {
                  var4 = (CodeLine)var3.get(var7);
                  var6 = var4.getLineCoordinates();
                  if (var6 != null) {
                     break;
                  }
               }

               if (var6 == null) {
                  return null;
               }
            }

            if (var5 instanceof InstructionCoordinates var11 && var11.getMethodId() < 0) {
               var5 = null;
            }

            Object var12 = var5;
            if (var5 == null) {
               var12 = var6;
            }

            if (var12 == null) {
               return null;
            } else {
               if (var6 instanceof InstructionCoordinates && var5 instanceof InstructionCoordinates) {
                  InstructionCoordinates var13 = (InstructionCoordinates)var6;
                  InstructionCoordinates var9 = (InstructionCoordinates)var5;
                  if (var13.getOffset() >= 0 && var9.getOffset() < 0) {
                     var12 = var6;
                  }
               }

               if (var12 instanceof InstructionCoordinates var14 && var14.getOffset() < 0) {
                  var12 = new MethodCoordinates(var14.getMethodId());
               }

               return (ICodeCoordinates)var12;
            }
         } else {
            return null;
         }
      }
   }

   @Override
   public ICoordinates addressToCoordinates(String var1, CoordinatesConversionPrecision var2) {
      IDexUnit var3 = (IDexUnit)((IDexDecompilerUnit)this.ast.getParent()).getParent();
      ICodeCoordinates var4 = var3.getCodeCoordinatesFromAddress(var1);
      if (var4 == null) {
         return null;
      } else {
         ListIterator var6 = Lists.reverseIterator(this.singlePart.getLines());

         while (var6.hasPrevious()) {
            ICodeCoordinates var7 = ((CodeLine)var6.previous()).getLineCoordinates();
            if (var4.equals(var7)) {
               return new Coordinates(0L, var6.nextIndex(), 0);
            }
         }

         int var17 = Integer.MAX_VALUE;
         int var8 = 0;
         int var9 = 0;
         int var5 = 0;

         for (CodeLine var11 : this.singlePart.getLines()) {
            ICodeCoordinates var12 = var11.getLineCoordinates();
            if (var4.equals(var12)) {
               return new Coordinates(0L, var5, 0);
            }

            int var13 = CodeCoordinatesUtil.distance(var4, var12);
            if (var13 < var17) {
               var17 = var13;
               var8 = var5;
               var9 = 0;
            }

            Map var14 = var11.getCoordinates();

            for (Integer var16 : var14.keySet()) {
               var12 = (ICodeCoordinates)var14.get(var16);
               if (var4.equals(var12)) {
                  return new Coordinates(0L, var5, var16);
               }

               var13 = CodeCoordinatesUtil.distance(var4, var12);
               if (var13 < var17) {
                  var17 = var13;
                  var8 = var5;
                  var9 = var16;
               }
            }

            var5++;
         }

         if (var17 < Integer.MAX_VALUE) {
            Object[] var10000 = new Object[]{var17};
            return new Coordinates(0L, var8, var9);
         } else {
            return null;
         }
      }
   }
}
