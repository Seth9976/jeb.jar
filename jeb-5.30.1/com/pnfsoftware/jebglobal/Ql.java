package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReference;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import java.util.Collection;
import java.util.Set;

class Ql {
   public static boolean q(INativeCodeAnalyzer var0, long var1) {
      INativeContinuousItem var3 = var0.getModel().getItemAt(var1);
      return var3 instanceof axu;
   }

   public static boolean q(INativeCodeAnalyzer var0, long var1, fA var3) {
      if (!q(var0, var3)) {
         return false;
      } else {
         try {
            Long var4 = var3.q().q(var3, var1, null);
            if (var4 != null) {
               if (!var0.getAnalysisRanges().contains(var4)) {
                  return false;
               }

               INativeContinuousItem var5 = var0.getModel().getItemAt(var4);
               if (var5 == null) {
                  return true;
               }

               if (var5 instanceof INativeDataItem && !(var5 instanceof axu) && q(var0, var5)) {
                  return false;
               }
            }

            return true;
         } catch (ProcessorException var6) {
            return false;
         }
      }
   }

   private static boolean q(INativeCodeAnalyzer var0, fA var1) {
      if (!OC.gP(var1)) {
         return false;
      } else if (var1.getProcessorMode() == 64) {
         return true;
      } else {
         String var2 = var1.Dw().q();
         switch (var2) {
            case "ADD":
            case "ADR":
            case "B":
            case "BL":
            case "BLX":
            case "BX":
            case "BXJ":
            case "CBNZ":
            case "CBZ":
            case "ERET":
            case "MOV":
            case "POP":
            case "RFE":
            case "RFEDA":
            case "RFEDB":
            case "RFEIA":
            case "RFEIB":
            case "SUB":
            case "TBB":
            case "TBH":
               return true;
            case "LDR":
               if (var1.RF()[0].RF(var0.getProcessor().getMode())) {
                  return true;
               }
               break;
            default:
               if (var2.startsWith("LDM") && !var1.RF()[0].RF(var0.getProcessor().getMode())) {
                  return true;
               }
         }

         return false;
      }
   }

   public static boolean q(INativeCodeAnalyzer var0, int var1, Collection var2, long var3, fA var5) {
      if (var1 == 64) {
         return false;
      } else {
         String var6 = var5.Dw().q();
         byte var8 = -1;
         switch (var6.hashCode()) {
            case 79409:
               if (var6.equals("POP")) {
                  var8 = 0;
               }
            default:
               switch (var8) {
                  case 0:
                     int var9 = 20;

                     for (fA var11 : var2) {
                        if (OC.q(var11, false)) {
                           return false;
                        }

                        if (--var9 == 0) {
                           break;
                        }
                     }

                     return true;
                  default:
                     return false;
               }
         }
      }
   }

   private static boolean q(INativeCodeAnalyzer var0, INativeContinuousItem var1) {
      if (!var1.getName().startsWith("gvar_") && !var1.getName().startsWith("loc_")) {
         return true;
      } else {
         Set var2 = var0.getModel().getReferenceManager().getReferencesTo(var1.getMemoryAddress());
         if (var2.isEmpty()) {
            return false;
         } else {
            if (var2.size() == 1) {
               IReference var3 = (IReference)var2.iterator().next();
               if (q(var0, var1, var3) || RF(var0, var1, var3)) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   private static boolean q(INativeCodeAnalyzer var0, INativeContinuousItem var1, IReference var2) {
      INativeContinuousItem var3 = var0.getModel().getPreviousItem(var1);
      if (var3 == null) {
         return false;
      } else if (var1.getMemoryAddress() - var3.getMemoryAddress() > 32L) {
         return false;
      } else if (var3 instanceof INativeInstructionItem) {
         return true;
      } else {
         if (var3 instanceof INativeDataItem) {
            Set var4 = var0.getModel().getReferenceManager().getReferencesTo(var3.getMemoryAddress());
            if (var4.size() == 1 && ((IReference)var4.iterator().next()).getFrom().equals(var2.getFrom())) {
               return q(var0, var3, var2);
            }
         }

         return false;
      }
   }

   private static boolean RF(INativeCodeAnalyzer var0, INativeContinuousItem var1, IReference var2) {
      INativeContinuousItem var3 = var0.getModel().getNextItem(var1);
      if (var3 == null) {
         return false;
      } else if (var3.getMemoryAddress() - var1.getMemoryAddress() > 32L) {
         return false;
      } else if (var3 instanceof INativeInstructionItem) {
         return true;
      } else {
         if (var3 instanceof INativeDataItem) {
            Set var4 = var0.getModel().getReferenceManager().getReferencesTo(var3.getMemoryAddress());
            if (var4.size() == 1 && ((IReference)var4.iterator().next()).getFrom().equals(var2.getFrom())) {
               return RF(var0, var3, var2);
            }
         }

         return false;
      }
   }
}
