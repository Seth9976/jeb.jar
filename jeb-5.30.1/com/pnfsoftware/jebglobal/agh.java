package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.coordinates.NativeCoordinates;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CIdentifierClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CSimulationException;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.lang.invoke.StringConcatFactory;
import java.util.List;

@Ser
public class agh extends afg implements ICIdentifier {
   @SerId(1)
   int q;
   @SerId(2)
   ICType RF;
   @SerId(3)
   String xK;
   @SerId(4)
   String Dw;
   @SerId(5)
   CIdentifierClass Uv;
   @SerId(6)
   Integer oW;
   @SerId(7)
   Long gO;
   @SerId(8)
   int nf;
   @SerId(9)
   Integer gP;

   agh(int var1, ICType var2, String var3, CIdentifierClass var4, Integer var5, Long var6, Integer var7) {
      if (var3 == null) {
         throw new NullPointerException();
      } else if (var5 == null && var4 == CIdentifierClass.LOCAL) {
         throw new IllegalArgumentException();
      } else if (var6 == null && var4 != CIdentifierClass.SYNTHETIC && var4 != CIdentifierClass.BUILTIN && var4 != CIdentifierClass.SPECIAL) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
         this.RF = var2;
         this.Dw = var3;
         this.xK = var3;
         this.Uv = var4;
         this.oW = var5;
         this.gO = var6;
         this.gP = var7;
      }
   }

   @Override
   public int getId() {
      return this.q;
   }

   @Override
   public ICType getType() {
      return this.RF;
   }

   @Override
   public String getOriginalName() {
      return this.xK;
   }

   @Override
   public String getName() {
      return this.Dw;
   }

   @Override
   public boolean setName(String var1) {
      return this.setName(var1, null);
   }

   @Override
   public boolean setName(String var1, IDynamicContentManager var2) {
      if (var2 != null && this.Uv != null) {
         if (this.gP != null) {
            var2.setParamName(this.oW, this.gP, var1);
         }

         switch (this.Uv) {
            case GLOBAL:
               if (this.gO != null) {
                  var2.setLabelName(new NativeCoordinates(this.gO), var1);
               }
               break;
            case LOCAL:
               if (this.oW != null && this.gO != null) {
                  var2.setLocalVariableName(this.oW, this.gO, var1);
               }
         }
      }

      if (var1 == null || var1.length() == 0) {
         var1 = this.xK;
      }

      this.Dw = var1;
      return true;
   }

   @Override
   public CIdentifierClass getIdentifierClass() {
      return this.Uv;
   }

   @Override
   public Integer getMethodIndex() {
      return this.oW;
   }

   @Override
   public Integer getParameterIndex() {
      return this.gP;
   }

   @Override
   public Long getAddress() {
      return this.gO;
   }

   @Override
   public void setFlags(int var1) {
      this.nf = var1;
   }

   @Override
   public int getFlags() {
      return this.nf;
   }

   @Override
   public void generate(COutputSink var1) {
      this.generate(var1, false);
   }

   @Override
   public void generate(COutputSink var1, boolean var2) {
      this.q(var1);
      String var3 = this.Dw;
      long var4 = 0L;
      IDynamicContentManager var6 = var1.getDynamicContentManager();
      if (var6 != null && this.Uv != null) {
         String var7 = null;
         if (this.gP != null) {
            var7 = var6.getParamName(this.oW, this.gP);
         }

         switch (this.Uv) {
            case GLOBAL:
               var3 = var6.getLabelName(new NativeCoordinates(this.gO));
               var4 = var6.getLabelItemId(new NativeCoordinates(this.gO));
               break;
            case LOCAL:
               var3 = var7 != null ? var7 : var6.getLocalVariableName(this.oW, this.gO);
               var4 = var6.getLocalVariableItemId(this.oW, this.gO);
               break;
            case SYNTHETIC:
               var3 = var7;
               var4 = var6.getSyntheticIdentifierItemId(this.oW, this.q);
         }

         if (var3 == null) {
            var3 = this.Dw;
         }
      }

      var1.appendAndRecord(var3, ItemClassIdentifiers.IDENTIFIER, var4, var2 ? 1 : 0);
      this.RF(var1);
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
      return CElementType.Identifier;
   }

   @Override
   public String toString() {
      return StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.Dw);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.q;
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      var1 = 31 * var1 + (this.Uv == null ? 0 : this.Uv.hashCode());
      var1 = 31 * var1 + (this.oW == null ? 0 : this.oW.hashCode());
      return 31 * var1 + (this.gO == null ? 0 : this.gO.hashCode());
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
         agh var2 = (agh)var1;
         if (this.q != var2.q) {
            return false;
         } else {
            if (this.Dw == null) {
               if (var2.Dw != null) {
                  return false;
               }
            } else if (!this.Dw.equals(var2.Dw)) {
               return false;
            }

            if (this.RF == null) {
               if (var2.RF != null) {
                  return false;
               }
            } else if (!this.RF.equals(var2.RF)) {
               return false;
            }

            if (this.Uv != var2.Uv) {
               return false;
            } else {
               if (this.oW == null) {
                  if (var2.oW != null) {
                     return false;
                  }
               } else if (!this.oW.equals(var2.oW)) {
                  return false;
               }

               if (this.gO == null) {
                  if (var2.gO != null) {
                     return false;
                  }
               } else if (!this.gO.equals(var2.gO)) {
                  return false;
               }

               return true;
            }
         }
      }
   }

   public agh RF() {
      return this;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      Long var3 = var1.getLocalVarValue(this);
      if (var3 == null) {
         if (this.Uv.isLocal()) {
            var3 = var2.getParameterValue(this);
            if (var3 == null) {
               throw new CSimulationException(Strings.ff("non initialized local identifier (%s)", this));
            }
         } else {
            var3 = var2.getGlobalVarValue(this, true);
            if (var3 == null) {
               throw new CSimulationException(Strings.ff("non initialized global identifier (%s)", this));
            }
         }
      }

      return var3;
   }
}
