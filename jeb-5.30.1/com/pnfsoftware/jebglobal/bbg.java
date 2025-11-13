package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.CallingConventionName;
import com.pnfsoftware.jeb.core.units.code.asm.type.CallingConventionService;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConventionManager;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Ser
public class bbg implements ICallingConventionManager {
   private static final ILogger q = GlobalLog.getLogger(bbg.class);
   @SerId(1)
   private List RF = new ArrayList();
   @SerId(2)
   private ProcessorType xK;
   @SerId(3)
   private ICallingConvention Dw;
   @SerId(4)
   private SubsystemType Uv;
   @SerId(5)
   private CompilerType oW;

   public bbg(ProcessorType var1, SubsystemType var2, CompilerType var3, String var4) {
      this.RF.addAll(CallingConventionService.getInstance().getConventions());
      if (var1 == null) {
         throw new NullPointerException("A processor type is needed to initialize the calling convention manager");
      } else {
         this.xK = var1;
         this.Uv = var2 == null ? SubsystemType.UNKNOWN : var2;
         this.oW = var3 == null ? CompilerType.UNKNOWN : var3;
         if (var4 == null) {
            if (this.xK == ProcessorType.UNKNOWN) {
               var4 = CallingConventionName.UNKNOWN.toString();
            } else if (this.xK == ProcessorType.ARM) {
               if (this.RF()) {
                  var4 = CallingConventionName.CDECL.toString();
               } else {
                  var4 = CallingConventionName.CDECL.toString();
               }
            } else if (this.xK == ProcessorType.ARM64) {
               if (this.RF()) {
                  var4 = CallingConventionName.CDECL.toString();
               } else {
                  var4 = CallingConventionName.CDECL.toString();
               }
            } else if (this.xK == ProcessorType.MIPS) {
               var4 = CallingConventionName.MIPS_O32.toString();
            } else if (this.xK == ProcessorType.MIPS64) {
               var4 = CallingConventionName.MIPS_N64.toString();
            } else if (this.xK == ProcessorType.X86) {
               var4 = CallingConventionName.CDECL.toString();
            } else if (this.xK == ProcessorType.X86_64) {
               if (this.RF()) {
                  var4 = CallingConventionName.STDCALL.toString();
               } else {
                  var4 = CallingConventionName.CDECL.toString();
               }
            } else if (this.xK == ProcessorType.AVR) {
               var4 = CallingConventionName.CDECL.toString();
            } else {
               var4 = CallingConventionName.CDECL.toString();
            }
         }

         this.Dw = this.q(var4, var1, var2, var3);
      }
   }

   private boolean RF() {
      return this.Uv.isWindowsLike() || this.oW == CompilerType.MSVC;
   }

   private ICallingConvention q(String var1, ProcessorType var2, SubsystemType var3, CompilerType var4) {
      if ((var2.isARM() || var2.isIntel()) && var3 == SubsystemType.UNKNOWN) {
         var3 = SubsystemType.LINUX;
      }

      byte var5 = -1;
      ICallingConvention var6 = null;

      for (ICallingConvention var8 : this.RF) {
         byte var9 = 0;
         if (var8.getNames().contains(var1)
            && (var2 == null || var8.getProcessorTypes().contains(var2) || var8.getProcessorTypes().contains(ProcessorType.UNKNOWN))) {
            if (var3 != null && var8.getSubsystemTypes().contains(var3)) {
               var9 += 20;
            }

            if (var4 != null && var8.getCompilerTypes().contains(var4)) {
               var9 += 10;
            }

            if (var9 > var5) {
               var5 = var9;
               var6 = var8;
               if (var9 == 30) {
                  break;
               }
            }
         }
      }

      return var6;
   }

   @Override
   public ProcessorType getProcessorType() {
      return this.xK;
   }

   @Override
   public SubsystemType getSubsystemType() {
      return this.Uv;
   }

   @Override
   public CompilerType getCompilerType() {
      return this.oW;
   }

   @Override
   public ICallingConvention getDefaultConvention() {
      return this.Dw;
   }

   @Override
   public void setDefaultConvention(ICallingConvention var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.Dw = var1;
      }
   }

   @Override
   public ICallingConvention getConvention(String var1) {
      return this.q(var1, this.xK, this.Uv, this.oW);
   }

   @Override
   public List getConventions() {
      return this.q();
   }

   public List q() {
      ArrayList var1 = new ArrayList();

      for (ICallingConvention var3 : this.RF) {
         if (var3.isCompatibleWith(this.xK, this.Uv, this.oW)) {
            var1.add(var3);
         }
      }

      return var1;
   }

   @Override
   public List getAllConventions() {
      return Collections.unmodifiableList(this.RF);
   }

   @Override
   public boolean addConvention(ICallingConvention var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         long var2 = var1.getIdentifierKey();

         for (ICallingConvention var5 : this.RF) {
            if (var5.getIdentifierKey() == var2) {
               q.error("Calling convention cannot be registered: %s. It collides with: %s.", var1, var5);
               return false;
            }
         }

         this.RF.add(var1);
         return true;
      }
   }

   @Override
   public boolean removeConvention(ICallingConvention var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         return this.RF.remove(var1);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("ccman(%d)", this.RF.size());
   }
}
