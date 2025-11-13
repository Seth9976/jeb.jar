package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.NativeCoordinates;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AutoLabelPolicy;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.DataStringUtil;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeStringItem;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IPECOFFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;

public class ack implements IDynamicContentManager {
   private C pC;
   private ace A;
   private Long kS = null;

   public ack(C var1, ace var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public long getObjectItemId(Object var1) {
      return this.A.A().generate(var1);
   }

   @Override
   public String getComment(ICodeCoordinates var1) {
      return this.pC.getCommentManager().formatComments2(var1, false, 0, 0);
   }

   @Override
   public String getPreComment(ICodeCoordinates var1) {
      return this.pC.getCommentManager().getPrimary2(var1, -1);
   }

   @Override
   public String getMethodName(ICodeCoordinates var1) {
      if (var1 instanceof NativeCoordinates) {
         long var2 = ((NativeCoordinates)var1).getAddress();
         auu var4 = this.pC.A(var2);
         if (var4 != null) {
            return var4.getName(true);
         }

         var2 = this.pC(var2);
         if (var2 != 0L) {
            var4 = this.pC.A(var2);
            if (var4 != null) {
               return var4.getName(true);
            }
         }
      } else if (var1 instanceof MethodCoordinates) {
         int var6 = ((MethodCoordinates)var1).getMethodId();
         auu var3 = this.pC.UT(var6);
         if (var3 != null) {
            return var3.getName(true);
         }
      }

      return null;
   }

   @Override
   public long getMethodItemId(ICodeCoordinates var1) {
      if (var1 instanceof NativeCoordinates) {
         long var2 = ((NativeCoordinates)var1).getAddress();
         auu var4 = this.pC.A(var2);
         if (var4 != null) {
            String var9 = this.pC.getSymbolicStringAddress(var4.E().getMemoryAddress());
            return this.pC.getItemAtAddress(var9);
         }

         var2 = this.pC(var2);
         if (var2 != 0L) {
            var4 = this.pC.A(var2);
            if (var4 != null) {
               String var5 = this.pC.getSymbolicStringAddress(var4.E().getMemoryAddress());
               return this.pC.getItemAtAddress(var5);
            }
         }
      } else if (var1 instanceof MethodCoordinates) {
         int var7 = ((MethodCoordinates)var1).getMethodId();
         auu var3 = this.pC.UT(var7);
         if (var3 != null) {
            return var3.getItemId();
         }
      }

      return 0L;
   }

   @Override
   public long getLabelItemId(ICodeCoordinates var1) {
      if (var1 instanceof NativeCoordinates) {
         long var2 = ((NativeCoordinates)var1).getAddress();
         if (var2 != 0L) {
            String var4 = this.pC.getSymbolicStringAddress(var2);
            long var5 = this.pC.getItemAtAddress(var4);
            if (var5 != 0L) {
               return var5;
            }

            var2 = this.pC(var2);
            if (var2 != 0L) {
               var4 = this.pC.getSymbolicStringAddress(var2);
               var5 = this.pC.getItemAtAddress(var4);
            }

            return var5;
         }
      }

      return 0L;
   }

   @Override
   public String getLabelName(ICodeCoordinates var1) {
      if (var1 instanceof NativeCoordinates) {
         long var2 = ((NativeCoordinates)var1).getAddress();
         if (this.pC.getVirtualImageBase() == 0L && this.kS == null && this.pC.getParent() instanceof ICodeObjectUnit) {
            ICodeObjectUnit var4 = (ICodeObjectUnit)this.pC.getParent();
            if (var4 instanceof IELFUnit || var4 instanceof IPECOFFUnit || var4 instanceof com.pnfsoftware.jeb.corei.parsers.macho.zp) {
               for (ISegmentInformation var6 : var4.getSections()) {
                  if (var6.getSizeInMemory() != 0L && var6.getOffsetInMemory() != 0L && (this.kS == null || this.kS > var6.getOffsetInMemory())) {
                     this.kS = var6.getOffsetInMemory();
                  }
               }
            }
         }

         if (var2 != 0L && (this.kS == null || var2 >= this.kS)) {
            String var8 = this.pC.getCodeModel().getLabelManager().getLabel(var2, 0L, AutoLabelPolicy.ITEM);
            if (var8 != null) {
               return var8;
            }

            var2 = this.pC(var2);
            if (var2 != 0L) {
               var8 = this.pC.getCodeModel().getLabelManager().getLabel(var2, 0L, AutoLabelPolicy.ITEM);
            }

            return var8;
         }
      }

      return null;
   }

   @Override
   public void setLabelName(ICodeCoordinates var1, String var2) {
      if (var1 instanceof NativeCoordinates) {
         long var3 = ((NativeCoordinates)var1).getAddress();
         if (var3 != 0L) {
            this.pC.pC(var3, var2);
            long var5 = this.pC(var3);
            if (var5 != 0L && var5 != var3) {
               this.pC.pC(var5, var2);
            }
         }
      }
   }

   protected long pC(long var1) {
      CodePointer var3 = this.pC.getProcessor().createEntryPoint(var1);
      if (var3.getAddress() != var1) {
         INativeContinuousItem var4 = this.pC.getNativeItemAt(var3.getAddress());
         if (var4 instanceof INativeInstructionItem) {
            return var3.getAddress();
         }
      }

      return 0L;
   }

   @Override
   public long getLocalVariableItemId(int var1, long var2) {
      return this.pC.pC(var1, (int)var2);
   }

   @Override
   public String getLocalVariableName(int var1, long var2) {
      long var4 = this.pC.pC(var1, (int)var2);
      if (var4 != 0L) {
         INativeItem var6 = this.pC.getItemObject(var4);
         if (var6 != null) {
            return var6.getName(true);
         }
      }

      return null;
   }

   @Override
   public void setLocalVariableName(int var1, long var2, String var4) {
      long var5 = this.pC.pC(var1, (int)var2);
      if (var5 != 0L) {
         INativeItem var7 = this.pC.getItemObject(var5);
         if (var7 != null) {
            var7.setName(var4);
         }
      }
   }

   @Override
   public String getParamName(int var1, int var2) {
      auu var3 = this.pC.UT(var1);
      return var3 == null ? null : var3.getParameterName(var2);
   }

   @Override
   public boolean setParamName(int var1, int var2, String var3) {
      auu var4 = this.pC.UT(var1);
      if (var4 == null) {
         return false;
      } else {
         var4.setParameterName(var2, var3);
         return true;
      }
   }

   @Override
   public String getPackageOfMethod(int var1) {
      auu var2 = this.pC.wS(var1);
      if (var2 != null) {
         ayp var3 = this.pC.A().pC((INativeItem)var2);
         if (var3 != null) {
            return var3.getSignature(true);
         }
      }

      return null;
   }

   @Override
   public long getTypeItemId(String var1) {
      aye var2 = this.pC.A().UT(var1);
      return var2 == null ? 0L : var2.getItemId();
   }

   @Override
   public String getTypeSignature(String var1) {
      aye var2 = this.pC.A().UT(var1);
      return var2 == null ? null : var2.getSignature(true);
   }

   @Override
   public long getSyntheticIdentifierItemId(int var1, int var2) {
      return -8502796096475496448L | (var1 & 16777215L) << 32 | var2 & 4294967295L;
   }

   @Override
   public long getStructureFieldItemId(String var1, int var2) {
      aye var3 = this.pC.A().UT(var1);
      return !(var3 instanceof ayv) ? 0L : this.pC.pC((ayv)var3, var2);
   }

   @Override
   public String getStructureFieldName(String var1, int var2) {
      aye var3 = this.pC.A().UT(var1);
      if (!(var3 instanceof ayv)) {
         return null;
      } else {
         ayu var4 = ((ayv)var3).A(var2);
         return var4 == null ? null : var4.getName();
      }
   }

   @Override
   public String getNativeInstructionFormat(long var1) {
      INativeContinuousItem var3 = this.pC.getNativeItemAt(var1);
      return !(var3 instanceof aus) ? null : ((aus)var3).getInstruction().format(var1);
   }

   @Override
   public INativeStringItem getNativeString(int var1) {
      return this.pC.kS(var1);
   }

   @Override
   public String getPotentialDataAsString(long var1) {
      INativeContinuousItem var3 = this.pC.getNativeItemAt(var1);
      return var3 != null && !var3.hasAttribute("GeneratedFrom") ? null : DataStringUtil.getStringAt(this.pC.getMemory(), var1, 4, -1);
   }
}
