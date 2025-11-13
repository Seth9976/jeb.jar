package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.coordinates.FieldCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.NativeCoordinates;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class aek extends aeh implements ICField {
   private static final StructuredLogger sY = aco.pC(aek.class);
   @SerId(1)
   private ICType ys;
   @SerId(2)
   private ICType ld;
   @SerId(3)
   private String gp;
   @SerId(4)
   private Integer oT;
   @SerId(5)
   private Long fI;

   public aek(ICGlobalContext var1, String var2) {
      super(var1, var2);
   }

   public void pC(int var1, ICType var2, ICType var3, String var4, int var5) {
      this.kS = false;
      this.wS = -1;
      this.A = var1;
      this.ys = var2;
      this.ld = var3;
      this.gp = var4 != null ? var4 : this.getAddress();
      this.oT = var5;
      this.fI = null;
      this.setStatusCode(0);
   }

   public void pC(boolean var1, int var2, int var3, ICType var4, String var5, Long var6) {
      this.kS = var1;
      this.wS = var2;
      this.A = var3;
      this.ys = null;
      this.ld = var4;
      this.gp = var5 != null ? var5 : this.getAddress();
      this.oT = null;
      this.fI = var6;
      this.setStatusCode(0);
   }

   public aek A() {
      return this;
   }

   @Override
   public ICType getOwnerType() {
      return this.ys;
   }

   @Override
   public ICType getType() {
      return this.ld;
   }

   @Override
   public String getName() {
      if (this.gp != null) {
         return this.gp;
      } else {
         String var1 = this.pC;
         int var2 = var1.lastIndexOf("::");
         if (var2 >= 0) {
            var1 = var1.substring(var2 + 2);
         }

         var2 = var1.indexOf("->");
         if (var2 >= 0) {
            var1 = var1.substring(var2 + 2);
         }

         return var1;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      if (!this.kS) {
         this.pC(var1);
         if (this.UT == 1) {
            var1.appendCommentAuto("// Uninitialized field: ");
            this.generateName(var1, true);
            var1.eol();
         } else {
            if (this.UT == 4) {
               var1.appendCommentAuto(
                  ckx.pC(
                     new byte[]{
                        20,
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
                     59
                  )
               );
               var1.eol();
            }

            this.kS(var1);
            FieldCoordinates var2 = new FieldCoordinates(this.wS);
            var1.setEolCoordinates(var2);
            this.wS(var1);
            var1.append(";");
            var1.eol();
         }

         this.A(var1);
      }
   }

   private void wS(COutputSink var1) {
      if (!var1.getSourceCustomizer().generateFieldDeclarationLine(this, var1)) {
         if (this.ld == null) {
            var1.append("???");
         } else {
            this.ld.generate(var1);
         }

         var1.space();
         this.generateName(var1, true);
      }
   }

   @Override
   public void generateName(COutputSink var1, boolean var2) {
      String var3 = null;
      long var4 = 0L;
      IDynamicContentManager var6 = var1.getDynamicContentManager();
      if (var6 != null) {
         if (this.ys != null) {
            var3 = var6.getStructureFieldName(this.ys.getSignature(), this.oT);
            var4 = var6.getStructureFieldItemId(this.ys.getSignature(), this.oT);
         } else if (this.fI != null) {
            var3 = var6.getLabelName(new NativeCoordinates(this.fI));
            var4 = var6.getLabelItemId(new NativeCoordinates(this.fI));
         }
      }

      if (var3 == null) {
         var3 = this.getName();
         var4 = 0L;
      }

      var1.appendAndRecord(var3, ItemClassIdentifiers.FIELD_NAME, var4, var2 ? 1 : 0);
   }

   @Override
   public List getSubElements() {
      return afm.pC();
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      return false;
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Field;
   }

   @Override
   public String toString() {
      return "field:" + this.getAddress();
   }
}
