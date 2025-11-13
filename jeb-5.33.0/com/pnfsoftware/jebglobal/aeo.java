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
public class aeo extends adn implements ICIdentifier {
   @SerId(1)
   int pC;
   @SerId(2)
   ICType A;
   @SerId(3)
   String kS;
   @SerId(4)
   String wS;
   @SerId(5)
   CIdentifierClass UT;
   @SerId(6)
   Integer E;
   @SerId(7)
   Long sY;
   @SerId(8)
   int ys;
   @SerId(9)
   Integer ld;

   aeo(int var1, ICType var2, String var3, CIdentifierClass var4, Integer var5, Long var6, Integer var7) {
      if (var3 == null) {
         throw new NullPointerException();
      } else if (var5 == null && var4 == CIdentifierClass.LOCAL) {
         throw new IllegalArgumentException();
      } else if (var6 == null && var4 != CIdentifierClass.SYNTHETIC && var4 != CIdentifierClass.BUILTIN && var4 != CIdentifierClass.SPECIAL) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
         this.A = var2;
         this.wS = var3;
         this.kS = var3;
         this.UT = var4;
         this.E = var5;
         this.sY = var6;
         this.ld = var7;
      }
   }

   @Override
   public int getId() {
      return this.pC;
   }

   @Override
   public ICType getType() {
      return this.A;
   }

   @Override
   public String getOriginalName() {
      return this.kS;
   }

   @Override
   public String getName() {
      return this.wS;
   }

   @Override
   public boolean setName(String var1) {
      return this.setName(var1, null);
   }

   @Override
   public boolean setName(String var1, IDynamicContentManager var2) {
      if (var2 != null && this.UT != null) {
         if (this.ld != null) {
            var2.setParamName(this.E, this.ld, var1);
         }

         switch (this.UT) {
            case GLOBAL:
               if (this.sY != null) {
                  var2.setLabelName(new NativeCoordinates(this.sY), var1);
               }
               break;
            case LOCAL:
               if (this.E != null && this.sY != null) {
                  var2.setLocalVariableName(this.E, this.sY, var1);
               }
         }
      }

      if (var1 == null || var1.length() == 0) {
         var1 = this.kS;
      }

      this.wS = var1;
      return true;
   }

   @Override
   public CIdentifierClass getIdentifierClass() {
      return this.UT;
   }

   @Override
   public Integer getMethodIndex() {
      return this.E;
   }

   @Override
   public Integer getParameterIndex() {
      return this.ld;
   }

   @Override
   public Long getAddress() {
      return this.sY;
   }

   @Override
   public void setFlags(int var1) {
      this.ys = var1;
   }

   @Override
   public int getFlags() {
      return this.ys;
   }

   @Override
   public void generate(COutputSink var1) {
      this.generate(var1, false);
   }

   @Override
   public void generate(COutputSink var1, boolean var2) {
      this.pC(var1);
      String var3 = this.wS;
      long var4 = 0L;
      IDynamicContentManager var6 = var1.getDynamicContentManager();
      if (var6 != null && this.UT != null) {
         String var7 = null;
         if (this.ld != null) {
            var7 = var6.getParamName(this.E, this.ld);
         }

         switch (this.UT) {
            case GLOBAL:
               var3 = var6.getLabelName(new NativeCoordinates(this.sY));
               var4 = var6.getLabelItemId(new NativeCoordinates(this.sY));
               break;
            case LOCAL:
               var3 = var7 != null ? var7 : var6.getLocalVariableName(this.E, this.sY);
               var4 = var6.getLocalVariableItemId(this.E, this.sY);
               break;
            case SYNTHETIC:
               var3 = var7;
               var4 = var6.getSyntheticIdentifierItemId(this.E, this.pC);
         }

         if (var3 == null) {
            var3 = this.wS;
         }
      }

      var1.appendAndRecord(var3, ItemClassIdentifiers.IDENTIFIER, var4, var2 ? 1 : 0);
      this.A(var1);
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
      return CElementType.Identifier;
   }

   @Override
   public String toString() {
      return StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.wS);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.pC;
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      var1 = 31 * var1 + (this.UT == null ? 0 : this.UT.hashCode());
      var1 = 31 * var1 + (this.E == null ? 0 : this.E.hashCode());
      return 31 * var1 + (this.sY == null ? 0 : this.sY.hashCode());
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
         aeo var2 = (aeo)var1;
         if (this.pC != var2.pC) {
            return false;
         } else {
            if (this.wS == null) {
               if (var2.wS != null) {
                  return false;
               }
            } else if (!this.wS.equals(var2.wS)) {
               return false;
            }

            if (this.A == null) {
               if (var2.A != null) {
                  return false;
               }
            } else if (!this.A.equals(var2.A)) {
               return false;
            }

            if (this.UT != var2.UT) {
               return false;
            } else {
               if (this.E == null) {
                  if (var2.E != null) {
                     return false;
                  }
               } else if (!this.E.equals(var2.E)) {
                  return false;
               }

               if (this.sY == null) {
                  if (var2.sY != null) {
                     return false;
                  }
               } else if (!this.sY.equals(var2.sY)) {
                  return false;
               }

               return true;
            }
         }
      }
   }

   public aeo A() {
      return this;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      Long var3 = var1.getLocalVarValue(this);
      if (var3 == null) {
         if (this.UT.isLocal()) {
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
