package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

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
import com.pnfsoftware.jeb.core.output.code.coordinates.NativeCoordinates;
import com.pnfsoftware.jeb.core.output.text.ICoordinates;
import com.pnfsoftware.jeb.core.output.text.impl.Coordinates;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionGroup;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeSourceUnit;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jebglobal.ace;
import com.pnfsoftware.jebglobal.aco;
import java.util.List;
import java.util.Map;

public class CDocument extends CodeDocument {
   private static final StructuredLogger logger = aco.pC(CDocument.class);
   public static final String propnameSpaceOutCompounds = "SpaceOutCompounds";
   public static final String propnameMergeAdjacentDefinitions = "MergeAdjacentDefinitions";
   public static final String propnameHideCasts = "HideCasts";
   public static final String propnameHideTopLevelNamespaceElements = "HideTopLevelNamespaceElements";
   boolean optionSpaceOutCompounds = false;
   boolean optionMergeAdjacentDefinitions = false;
   boolean optionHideCasts = false;
   boolean optionHideTopLevelNamespaceElements = true;
   private boolean disposed;
   private INativeCodeUnit codeunit;
   private IPropertyManager pm;
   private ace decomp;
   private INativeSourceUnit srcUnit;
   private IEventListener unitListener;
   private IEventListener pmListener;
   private COutputSink singlePart;

   public static void buildPDM(IPropertyDefinitionManager var0) {
      IPropertyDefinitionGroup var1 = var0.addGroup("text");
      var1.addDefinition("SpaceOutCompounds", PropertyTypeBoolean.create(true), S.L("Insert blank lines between compounds"));
      var1.addDefinition("MergeAdjacentDefinitions", PropertyTypeBoolean.create(), S.L("Merge same type definitions on a single line, e.g.: int i = 0, j = 1;"));
      var1.addDefinition("HideCasts", PropertyTypeBoolean.create(), S.L("Hide casts (the output will be incorrect)"));
      var1.addDefinition("HideTopLevelNamespaceElements", PropertyTypeBoolean.create(true), S.L("Hide top-level namespace elements in raw method names (C++)"));
   }

   public CDocument(INativeSourceUnit var1) {
      this.srcUnit = var1;
      this.decomp = (ace)var1.getParent();
      this.codeunit = (INativeCodeUnit)this.decomp.getParent();
      this.setupOptions(false);
      var1.addListener(this.unitListener = new CDocument$1(this));
      this.pm = this.decomp.getPropertyManager();
      this.pm.addListener(this.pmListener = new CDocument$2(this));
   }

   private void setupOptions(boolean var1) {
      IPropertyManager var2 = this.decomp.getPropertyManager();
      this.optionSpaceOutCompounds = var2.getBoolean("SpaceOutCompounds");
      this.optionMergeAdjacentDefinitions = var2.getBoolean("MergeAdjacentDefinitions");
      this.optionHideCasts = var2.getBoolean("HideCasts");
      this.optionHideTopLevelNamespaceElements = var2.getBoolean("HideTopLevelNamespaceElements");
      if (var1) {
         this.notifyListeners(new JebEvent(J.UnitChange));
      }
   }

   @Override
   public void dispose() {
      if (!this.disposed) {
         super.dispose();
         this.srcUnit.removeListener(this.unitListener);
         this.pm.removeListener(this.pmListener);
         this.disposed = true;
      }
   }

   @Override
   public IUnit getUnit() {
      return this.srcUnit;
   }

   @Override
   public long getAnchorCount() {
      return 1L;
   }

   public COutputSink getDocumentPart(long var1, int var3, int var4) {
      COutputSink var5 = new COutputSink(0L, this, this.decomp);
      var5.registerAnchor("single_anchor");
      ICElement var6 = this.srcUnit.getASTItem();
      if (!(var6 instanceof ICClass) && !(var6 instanceof ICMethod) && !(var6 instanceof ICField)) {
         throw new RuntimeException(Strings.ff("The generation of a top-level %s node is not supported yet", var6.getElementType()));
      } else {
         var5.setDynamicContentManager(this.decomp.pC());
         var5.setSourceCustomizer(this.decomp.wS());
         var6.generate(var5);
         var5.validate();
         this.singlePart = var5;
         return var5;
      }
   }

   @Override
   public String coordinatesToAddress(ICoordinates var1, AddressConversionPrecision var2) {
      ICodeCoordinates var3 = this.coordinatesToCodeCoordinates(var1);
      return var3 == null ? null : this.codeunit.getAddressFromCodeCoordinates(var3, var2);
   }

   private ICodeCoordinates coordinatesToCodeCoordinates(ICoordinates var1) {
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
               if (var6 instanceof NativeCoordinates && var5 instanceof NativeCoordinates) {
                  NativeCoordinates var13 = (NativeCoordinates)var6;
                  NativeCoordinates var9 = (NativeCoordinates)var5;
                  if (var13.getAddress() > var9.getAddress() && var9.getAddress() > 0L) {
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
      ICodeCoordinates var3 = this.codeunit.getCodeCoordinatesFromAddress(var1);
      if (var3 == null) {
         return null;
      } else {
         int var4 = Integer.MAX_VALUE;
         int var5 = 0;
         int var6 = 0;
         int var7 = 0;

         for (CodeLine var9 : this.singlePart.getLines()) {
            ICodeCoordinates var10 = var9.getLineCoordinates();
            if (var3.equals(var10)) {
               return new Coordinates(0L, var7, 0);
            }

            int var11 = CodeCoordinatesUtil.distance(var3, var10);
            if (var11 < var4) {
               var4 = var11;
               var5 = var7;
               var6 = 0;
            }

            Map var12 = var9.getCoordinates();

            for (Integer var14 : var12.keySet()) {
               var10 = (ICodeCoordinates)var12.get(var14);
               if (var3.equals(var10)) {
                  return new Coordinates(0L, var7, var14);
               }

               var11 = CodeCoordinatesUtil.distance(var3, var10);
               if (var11 < var4) {
                  var4 = var11;
                  var5 = var7;
                  var6 = var14;
               }
            }

            var7++;
         }

         if (var4 < Integer.MAX_VALUE) {
            Object[] var10000 = new Object[]{var4};
            return new Coordinates(0L, var5, var6);
         } else {
            return null;
         }
      }
   }
}
