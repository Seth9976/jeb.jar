package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.coordinates.ClassCoordinates;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CKeyword;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class adu extends aeh implements ICClass {
   private static final StructuredLogger sY = aco.pC(adu.class);
   @SerId(1)
   private ICType ys;
   @SerId(2)
   private List ld = new ArrayList();
   @SerId(3)
   private List gp = new ArrayList();
   @SerId(4)
   private List oT = new ArrayList();

   public adu(ICGlobalContext var1, String var2) {
      super(var1, var2);
   }

   public void pC(boolean var1, int var2, int var3, ICType var4, List var5) {
      this.kS = var1;
      this.wS = var2;
      this.A = var3;
      this.ys = var4;
      this.ld = var5 == null ? new ArrayList() : new ArrayList(var5);
      this.setStatusCode(0);
   }

   public adu A() {
      return this;
   }

   @Override
   public ICType getClasstype() {
      return this.ys;
   }

   @Override
   public List getSupertypes() {
      return this.ld;
   }

   @Override
   public List getFieldAddresses() {
      return this.gp;
   }

   @Override
   public List getFields() {
      ArrayList var1 = new ArrayList(this.gp.size());

      for (String var3 : this.gp) {
         ICField var4 = adj.pC(this, var3);
         if (var4 != null) {
            var1.add(var4);
         }
      }

      return var1;
   }

   @Override
   public void addField(ICField var1) {
      this.addField(this.gp.size(), var1);
   }

   @Override
   public void addField(int var1, ICField var2) {
      String var3 = var2.getAddress();
      if (!this.gp.contains(var3)) {
         this.gp.add(var1, var3);
      }
   }

   @Override
   public boolean removeField(ICField var1) {
      return this.gp.remove(var1.getAddress());
   }

   @Override
   public List getMethodAddresses() {
      return this.oT;
   }

   @Override
   public List getMethods() {
      ArrayList var1 = new ArrayList(this.oT.size());

      for (String var3 : this.oT) {
         ICMethod var4 = adj.A(this, var3);
         if (var4 != null) {
            var1.add(var4);
         }
      }

      return var1;
   }

   @Override
   public void addMethod(ICMethod var1) {
      this.addMethod(this.oT.size(), var1);
   }

   @Override
   public void addMethod(int var1, ICMethod var2) {
      String var3 = var2.getAddress();
      if (!this.oT.contains(var3)) {
         this.oT.add(var1, var3);
      }
   }

   @Override
   public boolean removeMethod(ICMethod var1) {
      return this.oT.remove(var1.getAddress());
   }

   @Override
   public void generate(COutputSink var1) {
      if (!this.kS) {
         this.pC(var1);
         var1.pushContainingClass(this);
         if (this.UT == 1) {
            var1.appendComment("// Uninitialized class");
            var1.eol();
         } else {
            if (this.UT == 4) {
               var1.appendAndRecord(
                  ckx.pC(
                     new byte[]{
                        108,
                        64,
                        80,
                        42,
                        6,
                        8,
                        11,
                        13,
                        84,
                        68,
                        77,
                        0,
                        70,
                        77,
                        65,
                        80,
                        85,
                        82,
                        88,
                        73,
                        93,
                        94,
                        18,
                        24,
                        12,
                        114,
                        42,
                        20,
                        19,
                        6,
                        31,
                        13,
                        0,
                        21,
                        6,
                        13,
                        92,
                        79,
                        4,
                        73,
                        12,
                        3,
                        83,
                        84,
                        14,
                        70,
                        20,
                        12,
                        65,
                        13,
                        4,
                        23,
                        10,
                        30,
                        94,
                        73,
                        45,
                        9,
                        76,
                        84,
                        26,
                        0,
                        20,
                        72,
                        23,
                        28,
                        68,
                        23
                     },
                     2,
                     41
                  ),
                  ItemClassIdentifiers.COMMENT
               );
               var1.eol();
            }

            IDynamicContentManager var2 = var1.getDynamicContentManager();
            if (var2 != null) {
               String var3 = var2.getPackageOfMethod(this.wS);
               if (var3 != null) {
                  var1.appendAndRecord("// Package: " + var3, ItemClassIdentifiers.COMMENT);
                  var1.eol();
                  var1.eol();
               }
            }

            this.kS(var1);
            ClassCoordinates var8 = new ClassCoordinates(this.wS);
            var1.setEolCoordinates(var8);
            this.wS(var1);
            var1.space();
            var1.brace();
            var1.eol();
            this.UT(var1);
            var1.incrementIndentationLevel();
            List var4 = this.getFields();
            if (!var4.isEmpty()) {
               for (ICField var6 : var4) {
                  var6.generate(var1);
               }

               var1.eol();
            }

            var1.decrementIndentationLevel();
            this.E(var1);
            var1.incrementIndentationLevel();
            List var9 = this.getMethods();
            if (!var9.isEmpty()) {
               for (ICMethod var7 : var9) {
                  var7.generate(var1);
                  var1.eol();
               }
            }

            var1.decrementIndentationLevel();
            var1.braceClose();
            var1.eol();
         }

         var1.popContainingClass();
         this.A(var1);
      }
   }

   private void wS(COutputSink var1) {
      if (!var1.getSourceCustomizer().generateClassDeclarationLine(this, var1)) {
         var1.appendKeyword(CKeyword.CLASS);
         var1.space();
         this.ys.generate(var1);
         if (!this.ld.isEmpty()) {
            var1.append(": ");
            int var2 = 0;

            for (ICType var4 : this.ld) {
               if (var2 > 0) {
                  var1.append(", ");
               }

               var1.appendKeyword(CKeyword.PUBLIC);
               var1.space();
               var4.generate(var1);
               var2++;
            }
         }
      }
   }

   private void UT(COutputSink var1) {
      if (!var1.getSourceCustomizer().preFieldsGeneration(this, var1)) {
         CKeyword.appendAccessKeyword(var1, CKeyword.PUBLIC);
         var1.append(":");
         var1.eol();
      }
   }

   private void E(COutputSink var1) {
      if (!var1.getSourceCustomizer().preMethodsGeneration(this, var1)) {
         ;
      }
   }

   @Override
   public List getSubElements() {
      List var1 = afm.pC();

      for (String var3 : this.gp) {
         ICField var4 = adj.pC(this, var3);
         if (var4 != null) {
            var1.add(var4);
         }
      }

      for (String var6 : this.oT) {
         ICMethod var7 = adj.A(this, var6);
         if (var7 != null) {
            var1.add(var7);
         }
      }

      return var1;
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      return false;
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Class;
   }

   @Override
   public String toString() {
      return "class:" + this.getAddress();
   }
}
