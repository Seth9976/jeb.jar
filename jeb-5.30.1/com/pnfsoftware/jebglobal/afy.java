package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCustomStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class afy extends afh implements ICCustomStatement {
   @SerId(1)
   long RF;
   @SerId(2)
   String xK;
   @SerId(3)
   List Dw;
   @SerId(4)
   List Uv;

   afy(long var1) {
      this.RF = var1;
   }

   public afy RF() {
      afy var1 = new afy(this.RF);
      super.q(var1);
      var1.xK = this.xK;
      if (this.Dw != null) {
         var1.Dw = new ArrayList(this.Dw.size());

         for (ICElement var3 : this.Dw) {
            var1.Dw.add(var3.duplicate());
         }
      }

      if (this.Uv != null) {
         var1.Uv = new ArrayList(this.Uv.size());

         for (ICElement var5 : this.Uv) {
            var1.Uv.add(var5.duplicate());
         }
      }

      return var1;
   }

   @Override
   public long getNativeAddress() {
      return this.RF;
   }

   @Override
   public void setCommandName(String var1) {
      this.xK = var1;
   }

   @Override
   public String getCommandName() {
      return this.xK;
   }

   @Override
   public void setInputElements(List var1) {
      this.Dw = var1 == null ? new ArrayList() : new ArrayList(var1);
   }

   @Override
   public List getInputElements() {
      return this.Dw;
   }

   @Override
   public void setOutputElements(List var1) {
      this.Uv = var1 == null ? new ArrayList() : new ArrayList(var1);
   }

   @Override
   public List getOutputElements() {
      return this.Uv;
   }

   @Override
   public void generate(COutputSink var1) {
      this.q(var1);
      if (!var1.getSourceCustomizer().generateNativeStatement(this, var1)) {
         if (this.xK != null) {
            if (this.Uv != null && !this.Uv.isEmpty()) {
               if (this.Uv.size() >= 2) {
                  var1.paren();
               }

               for (int var2 = 0; var2 < this.Uv.size(); var2++) {
                  if (var2 >= 1) {
                     var1.append(", ");
                  }

                  ((ICElement)this.Uv.get(var2)).generate(var1);
               }

               if (this.Uv.size() >= 2) {
                  var1.parenClose();
               }

               var1.space();
               var1.append("=");
               var1.space();
            }

            var1.append(this.xK);
            if (this.Dw != null) {
               var1.paren();

               for (int var5 = 0; var5 < this.Dw.size(); var5++) {
                  if (var5 >= 1) {
                     var1.append(", ");
                  }

                  ((ICElement)this.Dw.get(var5)).generate(var1);
               }

               var1.parenClose();
            }
         } else {
            String var6 = Strings.ff("instruction@%Xh", this.RF);
            IDynamicContentManager var3 = var1.getDynamicContentManager();
            if (var3 != null) {
               String var4 = var3.getNativeInstructionFormat(this.RF);
               if (var4 != null) {
                  var6 = Strings.ff("\"%s\"", var4);
               }
            }

            var1.append("__asm");
            var1.paren();
            var1.append(var6);
            var1.parenClose();
         }
      }

      this.RF(var1);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.CustomStatement;
   }

   @Override
   public List getSubElements() {
      List var1 = ahf.q();
      if (this.Dw != null) {
         return ahf.q(var1, this.Dw);
      } else {
         return this.Uv != null ? ahf.q(var1, this.Uv) : var1;
      }
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.Dw != null) {
         for (int var3 = 0; var3 < this.Dw.size(); var3++) {
            if (this.Dw.get(var3) == var1) {
               if (!(var2 instanceof ICElement)) {
                  return false;
               }

               this.Dw.set(var3, var2);
               return true;
            }
         }
      }

      if (this.Uv != null) {
         for (int var4 = 0; var4 < this.Uv.size(); var4++) {
            if (this.Uv.get(var4) == var1) {
               if (!(var2 instanceof ICElement)) {
                  return false;
               }

               this.Uv.set(var4, var2);
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (int)(this.RF ^ this.RF >>> 32);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         afy var2 = (afy)var1;
         return this.RF == var2.RF;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("0x%X:%s", this.RF, this.xK);
   }
}
