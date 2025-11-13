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
public class ayh implements ICallingConventionManager {
   private static final ILogger pC = GlobalLog.getLogger(ayh.class);
   @SerId(1)
   private List A = new ArrayList();
   @SerId(2)
   private ProcessorType kS;
   @SerId(3)
   private ICallingConvention wS;
   @SerId(4)
   private SubsystemType UT;
   @SerId(5)
   private CompilerType E;

   public ayh(ProcessorType var1, SubsystemType var2, CompilerType var3, String var4) {
      this.A.addAll(CallingConventionService.getInstance().getConventions());
      if (var1 == null) {
         throw new NullPointerException("A processor type is needed to initialize the calling convention manager");
      } else {
         this.kS = var1;
         this.UT = var2 == null ? SubsystemType.UNKNOWN : var2;
         this.E = var3 == null ? CompilerType.UNKNOWN : var3;
         if (var4 == null) {
            if (this.kS == ProcessorType.UNKNOWN) {
               var4 = CallingConventionName.UNKNOWN.toString();
            } else if (this.kS == ProcessorType.ARM) {
               if (this.A()) {
                  var4 = CallingConventionName.CDECL.toString();
               } else {
                  var4 = CallingConventionName.CDECL.toString();
               }
            } else if (this.kS == ProcessorType.ARM64) {
               if (this.A()) {
                  var4 = CallingConventionName.CDECL.toString();
               } else {
                  var4 = CallingConventionName.CDECL.toString();
               }
            } else if (this.kS == ProcessorType.MIPS) {
               var4 = CallingConventionName.MIPS_O32.toString();
            } else if (this.kS == ProcessorType.MIPS64) {
               var4 = CallingConventionName.MIPS_N64.toString();
            } else if (this.kS == ProcessorType.X86) {
               var4 = CallingConventionName.CDECL.toString();
            } else if (this.kS == ProcessorType.X86_64) {
               if (this.A()) {
                  var4 = CallingConventionName.STDCALL.toString();
               } else {
                  var4 = CallingConventionName.CDECL.toString();
               }
            } else if (this.kS == ProcessorType.AVR) {
               var4 = CallingConventionName.CDECL.toString();
            } else {
               var4 = CallingConventionName.CDECL.toString();
            }
         }

         this.wS = this.pC(var4, var1, var2, var3);
      }
   }

   private boolean A() {
      return this.UT.isWindowsLike() || this.E == CompilerType.MSVC;
   }

   private ICallingConvention pC(String var1, ProcessorType var2, SubsystemType var3, CompilerType var4) {
      if ((var2.isARM() || var2.isIntel()) && var3 == SubsystemType.UNKNOWN) {
         var3 = SubsystemType.LINUX;
      }

      byte var5 = -1;
      ICallingConvention var6 = null;

      for (ICallingConvention var8 : this.A) {
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
      return this.kS;
   }

   @Override
   public SubsystemType getSubsystemType() {
      return this.UT;
   }

   @Override
   public CompilerType getCompilerType() {
      return this.E;
   }

   @Override
   public ICallingConvention getDefaultConvention() {
      return this.wS;
   }

   @Override
   public void setDefaultConvention(ICallingConvention var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.wS = var1;
      }
   }

   @Override
   public ICallingConvention getConvention(String var1) {
      return this.pC(var1, this.kS, this.UT, this.E);
   }

   @Override
   public List getConventions() {
      return this.pC();
   }

   public List pC() {
      ArrayList var1 = new ArrayList();

      for (ICallingConvention var3 : this.A) {
         if (var3.isCompatibleWith(this.kS, this.UT, this.E)) {
            var1.add(var3);
         }
      }

      return var1;
   }

   @Override
   public List getAllConventions() {
      return Collections.unmodifiableList(this.A);
   }

   @Override
   public boolean addConvention(ICallingConvention var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         long var2 = var1.getIdentifierKey();

         for (ICallingConvention var5 : this.A) {
            if (var5.getIdentifierKey() == var2) {
               pC.error("Calling convention cannot be registered: %s. It collides with: %s.", var1, var5);
               return false;
            }
         }

         this.A.add(var1);
         return true;
      }
   }

   @Override
   public boolean removeConvention(ICallingConvention var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         return this.A.remove(var1);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("ccman(%d)", this.A.size());
   }
}
