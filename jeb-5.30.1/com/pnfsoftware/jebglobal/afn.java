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
public class afn extends aga implements ICClass {
   private static final StructuredLogger gO = aeg.q(afn.class);
   @SerId(1)
   private ICType nf;
   @SerId(2)
   private List gP = new ArrayList();
   @SerId(3)
   private List za = new ArrayList();
   @SerId(4)
   private List lm = new ArrayList();

   public afn(ICGlobalContext var1, String var2) {
      super(var1, var2);
   }

   public void q(boolean var1, int var2, int var3, ICType var4, List var5) {
      this.xK = var1;
      this.Dw = var2;
      this.RF = var3;
      this.nf = var4;
      this.gP = var5 == null ? new ArrayList() : new ArrayList(var5);
      this.setStatusCode(0);
   }

   public afn RF() {
      return this;
   }

   @Override
   public ICType getClasstype() {
      return this.nf;
   }

   @Override
   public List getSupertypes() {
      return this.gP;
   }

   @Override
   public List getFieldAddresses() {
      return this.za;
   }

   @Override
   public List getFields() {
      ArrayList var1 = new ArrayList(this.za.size());

      for (String var3 : this.za) {
         ICField var4 = afc.RF(this, var3);
         if (var4 != null) {
            var1.add(var4);
         }
      }

      return var1;
   }

   @Override
   public void addField(ICField var1) {
      this.addField(this.za.size(), var1);
   }

   @Override
   public void addField(int var1, ICField var2) {
      String var3 = var2.getAddress();
      if (!this.za.contains(var3)) {
         this.za.add(var1, var3);
      }
   }

   @Override
   public boolean removeField(ICField var1) {
      return this.za.remove(var1.getAddress());
   }

   @Override
   public List getMethodAddresses() {
      return this.lm;
   }

   @Override
   public List getMethods() {
      ArrayList var1 = new ArrayList(this.lm.size());

      for (String var3 : this.lm) {
         ICMethod var4 = afc.xK(this, var3);
         if (var4 != null) {
            var1.add(var4);
         }
      }

      return var1;
   }

   @Override
   public void addMethod(ICMethod var1) {
      this.addMethod(this.lm.size(), var1);
   }

   @Override
   public void addMethod(int var1, ICMethod var2) {
      String var3 = var2.getAddress();
      if (!this.lm.contains(var3)) {
         this.lm.add(var1, var3);
      }
   }

   @Override
   public boolean removeMethod(ICMethod var1) {
      return this.lm.remove(var1.getAddress());
   }

   @Override
   public void generate(COutputSink var1) {
      if (!this.xK) {
         this.q(var1);
         var1.pushContainingClass(this);
         if (this.Uv == 1) {
            var1.appendComment("// Uninitialized class");
            var1.eol();
         } else {
            if (this.Uv == 4) {
               var1.appendAndRecord(
                  cvm.q(
                     new byte[]{
                        101,
                        0,
                        15,
                        115,
                        39,
                        21,
                        13,
                        9,
                        69,
                        68,
                        1,
                        6,
                        12,
                        2,
                        29,
                        25,
                        5,
                        13,
                        21,
                        29,
                        6,
                        1,
                        78,
                        13,
                        13,
                        114,
                        55,
                        3,
                        20,
                        23,
                        22,
                        27,
                        72,
                        84,
                        28,
                        1,
                        26,
                        83,
                        86,
                        31,
                        12,
                        18,
                        87,
                        84,
                        27,
                        79,
                        82,
                        23,
                        72,
                        73,
                        1,
                        6,
                        12,
                        2,
                        29,
                        25,
                        5,
                        9,
                        69,
                        84,
                        28,
                        1,
                        26,
                        83,
                        67,
                        12,
                        11,
                        1
                     },
                     1,
                     74
                  ),
                  ItemClassIdentifiers.COMMENT
               );
               var1.eol();
            }

            IDynamicContentManager var2 = var1.getDynamicContentManager();
            if (var2 != null) {
               String var3 = var2.getPackageOfMethod(this.Dw);
               if (var3 != null) {
                  var1.appendAndRecord("// Package: " + var3, ItemClassIdentifiers.COMMENT);
                  var1.eol();
                  var1.eol();
               }
            }

            this.xK(var1);
            ClassCoordinates var8 = new ClassCoordinates(this.Dw);
            var1.setEolCoordinates(var8);
            this.Dw(var1);
            var1.space();
            var1.brace();
            var1.eol();
            this.Uv(var1);
            var1.incrementIndentationLevel();
            List var4 = this.getFields();
            if (!var4.isEmpty()) {
               for (ICField var6 : var4) {
                  var6.generate(var1);
               }

               var1.eol();
            }

            var1.decrementIndentationLevel();
            this.oW(var1);
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
         this.RF(var1);
      }
   }

   private void Dw(COutputSink var1) {
      if (!var1.getSourceCustomizer().generateClassDeclarationLine(this, var1)) {
         var1.appendKeyword(CKeyword.CLASS);
         var1.space();
         this.nf.generate(var1);
         if (!this.gP.isEmpty()) {
            var1.append(": ");
            int var2 = 0;

            for (ICType var4 : this.gP) {
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

   private void Uv(COutputSink var1) {
      if (!var1.getSourceCustomizer().preFieldsGeneration(this, var1)) {
         CKeyword.appendAccessKeyword(var1, CKeyword.PUBLIC);
         var1.append(":");
         var1.eol();
      }
   }

   private void oW(COutputSink var1) {
      if (!var1.getSourceCustomizer().preMethodsGeneration(this, var1)) {
         ;
      }
   }

   @Override
   public List getSubElements() {
      List var1 = ahf.q();

      for (String var3 : this.za) {
         ICField var4 = afc.RF(this, var3);
         if (var4 != null) {
            var1.add(var4);
         }
      }

      for (String var6 : this.lm) {
         ICMethod var7 = afc.xK(this, var6);
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
