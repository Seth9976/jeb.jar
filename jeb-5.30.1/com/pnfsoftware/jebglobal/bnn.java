package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.coordinates.FieldCoordinates;
import com.pnfsoftware.jeb.core.units.code.java.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGlobalContext;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bnn extends bmk implements IJavaField {
   public static final int Dw = -1;
   public static final int Uv = -2;
   @SerId(1)
   private int oW;
   @SerId(2)
   private boolean gO;
   @SerId(3)
   private String nf;
   @SerId(4)
   private String gP;
   @SerId(5)
   private IJavaType za;
   @SerId(6)
   private IJavaType lm;
   @SerId(7)
   private int zz;
   @SerId(8)
   private IJavaExpression JY;
   @SerId(9)
   private List HF;
   @SerId(10)
   @Deprecated
   private int LK;

   @SerCustomInitPostGraph
   private void Uv() {
      if (this.LK != 0) {
         this.addFlags(this.LK);
         this.LK = 0;
      }
   }

   public bnn(int var1, boolean var2, String var3, IJavaType var4, String var5, IJavaType var6, int var7) {
      this.oW = var1;
      this.gO = var2;
      this.nf = var3;
      this.za = var4;
      this.gP = var5;
      this.lm = var6;
      this.zz = var7;
   }

   @Override
   public IJavaGlobalContext getGlobalContext() {
      return this.d_() != null ? ((com.pnfsoftware.jeb.corei.parsers.dexdec.ej)this.d_()).RF() : null;
   }

   @Override
   public boolean isExternal() {
      return this.gO;
   }

   @Override
   public int getAccessFlags() {
      return this.zz;
   }

   @Override
   public boolean isStatic() {
      return (this.zz & 8) != 0;
   }

   @Override
   public boolean isSynthetic() {
      return (this.zz & 4096) != 0;
   }

   public int xK() {
      return this.oW;
   }

   @Override
   public String getName() {
      return this.gP;
   }

   public String xK(JavaOutputSink var1) {
      String var2 = this.gP;
      IDynamicContentManager var3 = var1.getDynamicContentManager();
      if (var3 != null && this.oW >= 0) {
         var2 = var3.getFieldName(this.oW);
      }

      return var2;
   }

   @Override
   public String getSignature() {
      return this.oW < 0 ? null : this.nf;
   }

   @Override
   public IJavaType getClassType() {
      return this.za;
   }

   @Override
   public IJavaType getType() {
      return this.lm;
   }

   public void q(IJavaExpression var1) {
      this.JY = var1;
   }

   @Override
   public IJavaExpression getInitialValue() {
      return this.JY;
   }

   public void Dw(List var1) {
      this.HF = var1;
   }

   @Override
   public List getAnnotations() {
      return this.HF;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      if (this.gO) {
         throw new RuntimeException("Cannot generate an external field");
      } else {
         this.q(var1);
         FieldCoordinates var2 = new FieldCoordinates(this.oW);
         var1.renderPreComment(var2);
         if (this.HF != null && var1.getGenerateAnnotations()) {
            var1.appendAnnotationsList(this.HF, '\n');
         }

         var1.recordGeneratedDecompilable(this.getSignature());
         JavaKeyword.generateFieldAccessFlags(var1, this.zz, -1);
         blk.q(var1, this.lm);
         var1.space();
         this.generateName(var1, true);
         if (this.JY != null) {
            var1.append(" = ");
            this.JY.generate(var1);
         }

         var1.append(";");
         String[] var3 = new String[1];
         boolean var4 = false;
         if (!var1.getDisregardCollapse()) {
            IDynamicContentManager var5 = var1.getDynamicContentManager();
            var4 = var5 != null && var5.isCollapsed(this.getSignature(), var3);
         }

         if (var4) {
            var1.getCurrentLine().addFlags(1);
            String var7 = var3[0];
            if (!Strings.isBlank(var7)) {
               var1.appendCommentAuto(" // " + var7);
            }
         }

         IDynamicContentManager var8 = var1.getDynamicContentManager();
         if (var8 != null) {
            String var6 = var8.getComment(var2);
            if (var6 != null) {
               var1.setEolComment(var6);
            }
         }

         var1.setEolCoordinates(var2);
         var1.eol();
         this.RF(var1);
      }
   }

   @Override
   public void generateName(JavaOutputSink var1, boolean var2) {
      this.generateName(var1, var2, null, false);
   }

   @Override
   public void generateName(JavaOutputSink var1, boolean var2, String var3, boolean var4) {
      String var5 = this.gP;
      long var6 = 0L;
      IDynamicContentManager var8 = var1.getDynamicContentManager();
      if (var8 != null && this.oW >= 0) {
         if (var1.getResolveFieldAccessTargets()) {
            var6 = var8.getImplFieldId(this.oW);
         } else {
            var6 = var8.getFieldId(this.oW);
         }

         if (!var8.wasFieldRenamed(this.oW) && var3 != null) {
            var5 = var3;
         } else {
            var5 = var8.getFieldName(this.oW);
         }
      }

      ItemClassIdentifiers var9 = var4 ? ItemClassIdentifiers.FIELD_NAME_GENERATED : ItemClassIdentifiers.FIELD_NAME;
      var1.appendAndRecord(var5, var9, var6, var2 ? 1 : 0);
   }

   @Override
   public List getSubElements() {
      return blo.q(this.JY);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.JY == var1) {
         if (var2 != null && !(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.JY = (IJavaExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Field;
   }

   @Override
   public int hashCode() {
      return super.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1;
   }

   @Override
   public String toString() {
      return "field:" + this.getName();
   }

   public bnn Dw() {
      return this;
   }
}
