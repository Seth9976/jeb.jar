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
public class agd extends aga implements ICField {
   private static final StructuredLogger gO = aeg.q(agd.class);
   @SerId(1)
   private ICType nf;
   @SerId(2)
   private ICType gP;
   @SerId(3)
   private String za;
   @SerId(4)
   private Integer lm;
   @SerId(5)
   private Long zz;

   public agd(ICGlobalContext var1, String var2) {
      super(var1, var2);
   }

   public void q(int var1, ICType var2, ICType var3, String var4, int var5) {
      this.xK = false;
      this.Dw = -1;
      this.RF = var1;
      this.nf = var2;
      this.gP = var3;
      this.za = var4 != null ? var4 : this.getAddress();
      this.lm = var5;
      this.zz = null;
      this.setStatusCode(0);
   }

   public void q(boolean var1, int var2, int var3, ICType var4, String var5, Long var6) {
      this.xK = var1;
      this.Dw = var2;
      this.RF = var3;
      this.nf = null;
      this.gP = var4;
      this.za = var5 != null ? var5 : this.getAddress();
      this.lm = null;
      this.zz = var6;
      this.setStatusCode(0);
   }

   public agd RF() {
      return this;
   }

   @Override
   public ICType getOwnerType() {
      return this.nf;
   }

   @Override
   public ICType getType() {
      return this.gP;
   }

   @Override
   public String getName() {
      if (this.za != null) {
         return this.za;
      } else {
         String var1 = this.q;
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
      if (!this.xK) {
         this.q(var1);
         if (this.Uv == 1) {
            var1.appendCommentAuto("// Uninitialized field: ");
            this.generateName(var1, true);
            var1.eol();
         } else {
            if (this.Uv == 4) {
               var1.appendCommentAuto(
                  cvm.q(
                     new byte[]{
                        -41,
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
                     248
                  )
               );
               var1.eol();
            }

            this.xK(var1);
            FieldCoordinates var2 = new FieldCoordinates(this.Dw);
            var1.setEolCoordinates(var2);
            this.Dw(var1);
            var1.append(";");
            var1.eol();
         }

         this.RF(var1);
      }
   }

   private void Dw(COutputSink var1) {
      if (!var1.getSourceCustomizer().generateFieldDeclarationLine(this, var1)) {
         if (this.gP == null) {
            var1.append("???");
         } else {
            this.gP.generate(var1);
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
         if (this.nf != null) {
            var3 = var6.getStructureFieldName(this.nf.getSignature(), this.lm);
            var4 = var6.getStructureFieldItemId(this.nf.getSignature(), this.lm);
         } else if (this.zz != null) {
            var3 = var6.getLabelName(new NativeCoordinates(this.zz));
            var4 = var6.getLabelItemId(new NativeCoordinates(this.zz));
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
      return ahf.q();
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
