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
public class bjq extends bin implements IJavaField {
   @SerId(1)
   private int A;
   @SerId(2)
   private boolean kS;
   @SerId(3)
   private String wS;
   @SerId(4)
   private String UT;
   @SerId(5)
   private IJavaType E;
   @SerId(6)
   private IJavaType sY;
   @SerId(7)
   private int ys;
   @SerId(8)
   private IJavaExpression ld;
   @SerId(9)
   private List gp;
   @SerId(10)
   @Deprecated
   private int oT;

   @SerCustomInitPostGraph
   private void wS() {
      if (this.oT != 0) {
         this.addFlags(this.oT);
         this.oT = 0;
      }
   }

   public bjq(int var1, boolean var2, String var3, IJavaType var4, String var5, IJavaType var6, int var7) {
      this.A = var1;
      this.kS = var2;
      this.wS = var3;
      this.E = var4;
      this.UT = var5;
      this.sY = var6;
      this.ys = var7;
   }

   @Override
   public IJavaGlobalContext getGlobalContext() {
      return this.d_() != null ? ((com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)this.d_()).A() : null;
   }

   @Override
   public boolean isExternal() {
      return this.kS;
   }

   @Override
   public int getAccessFlags() {
      return this.ys;
   }

   @Override
   public boolean isStatic() {
      return (this.ys & 8) != 0;
   }

   @Override
   public boolean isSynthetic() {
      return (this.ys & 4096) != 0;
   }

   public int A() {
      return this.A;
   }

   @Override
   public String getName() {
      return this.UT;
   }

   public String kS(JavaOutputSink var1) {
      String var2 = this.UT;
      IDynamicContentManager var3 = var1.getDynamicContentManager();
      if (var3 != null && this.A >= 0) {
         var2 = var3.getFieldName(this.A);
      }

      return var2;
   }

   @Override
   public String getSignature() {
      return this.A < 0 ? null : this.wS;
   }

   @Override
   public IJavaType getClassType() {
      return this.E;
   }

   @Override
   public IJavaType getType() {
      return this.sY;
   }

   public void pC(IJavaExpression var1) {
      this.ld = var1;
   }

   @Override
   public IJavaExpression getInitialValue() {
      return this.ld;
   }

   public void wS(List var1) {
      this.gp = var1;
   }

   @Override
   public List getAnnotations() {
      return this.gp;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      if (this.kS) {
         throw new RuntimeException("Cannot generate an external field");
      } else {
         this.pC(var1);
         FieldCoordinates var2 = new FieldCoordinates(this.A);
         var1.renderPreComment(var2);
         if (this.gp != null && var1.getGenerateAnnotations()) {
            var1.appendAnnotationsList(this.gp, '\n');
         }

         var1.recordGeneratedDecompilable(this.getSignature());
         JavaKeyword.generateFieldAccessFlags(var1, this.ys, -1);
         bhn.pC(var1, this.sY);
         var1.space();
         this.generateName(var1, true);
         if (this.ld != null) {
            var1.append(" = ");
            this.ld.generate(var1);
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
         this.A(var1);
      }
   }

   @Override
   public void generateName(JavaOutputSink var1, boolean var2) {
      this.generateName(var1, var2, null, false);
   }

   @Override
   public void generateName(JavaOutputSink var1, boolean var2, String var3, boolean var4) {
      String var5 = this.UT;
      long var6 = 0L;
      IDynamicContentManager var8 = var1.getDynamicContentManager();
      if (var8 != null && this.A >= 0) {
         if (var1.getResolveFieldAccessTargets()) {
            var6 = var8.getImplFieldId(this.A);
         } else {
            var6 = var8.getFieldId(this.A);
         }

         if (!var8.wasFieldRenamed(this.A) && var3 != null) {
            var5 = var3;
         } else {
            var5 = var8.getFieldName(this.A);
         }
      }

      ItemClassIdentifiers var9 = var4 ? ItemClassIdentifiers.FIELD_NAME_GENERATED : ItemClassIdentifiers.FIELD_NAME;
      var1.appendAndRecord(var5, var9, var6, var2 ? 1 : 0);
   }

   @Override
   public List getSubElements() {
      return bhr.pC(this.ld);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.ld == var1) {
         if (var2 != null && !(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.ld = (IJavaExpression)var2;
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

   public bjq kS() {
      return this;
   }
}
