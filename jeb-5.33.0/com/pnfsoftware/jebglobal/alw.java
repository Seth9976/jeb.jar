package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.PreRoutineInvocationDetails;
import com.pnfsoftware.jeb.core.units.code.asm.items.DataStringUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStorageEntryGenerator;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.core.units.code.asm.type.StringEncoding;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

public class alw {
   private static final StructuredLogger ys = aco.pC(alw.class);
   IERoutineContext pC;
   IEAssign A;
   PreRoutineInvocationDetails kS;
   ICallingConvention wS;
   List UT;
   int E;
   String sY;

   public alw(IERoutineContext var1, IEAssign var2, PreRoutineInvocationDetails var3, ICallingConvention var4, List var5) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
      this.UT = var5;
   }

   public int pC() {
      return this.E;
   }

   public boolean pC(boolean var1) {
      ArrayList var3 = new ArrayList(this.UT.size());
      IStorageEntryGenerator var4 = this.wS.getInputsGenerator();

      for (IWildcardType var6 : this.UT) {
         StorageEntry var7 = var4.next(var6.getLayoutInfo());
         if (var7 == null) {
            return false;
         }

         var3.add(var7);
      }

      int var14 = -1;

      for (int var2 = this.UT.size() - 1; var2 >= 0; var2--) {
         StorageEntry var15 = (StorageEntry)var3.get(var2);

         try {
            IEImm var16 = this.kS.readArg(this.pC.getConverter(), var15);
            if (var16 != null && var16.canReadAsAddress()) {
               long var8 = var16.getValueAsAddress();
               if (var8 != 0L) {
                  StringEncoding[] var10 = new StringEncoding[1];
                  String var11 = DataStringUtil.getStringAt(this.kS.getVirtualMemory(), var8, 4, -1, var10);
                  if (var11 != null && this.pC(var11)) {
                     var14 = var2;
                     this.sY = var11;
                     break;
                  }
               }
            }
         } catch (Exception var12) {
            ys.catching(var12);
            break;
         }

         if (!var1) {
            break;
         }
      }

      if (var14 < 0) {
         return false;
      } else {
         int var13 = var14 + 1;
         this.E = this.UT.size() - var13;
         return true;
      }
   }

   private boolean pC(String var1) {
      int var2 = 0;

      while (true) {
         label47: {
            if (var2 < var1.length() - 1) {
               char var3 = var1.charAt(var2);
               if (var3 != '%') {
                  break label47;
               }

               char var4 = var1.charAt(var2 + 1);
               if (var4 == 'd' || var4 == 'c' || var4 == 'u' || var4 == 'p' || var4 == 'x' || var4 == 'X' || var4 == 'f') {
                  return true;
               }

               if (var4 == '%') {
                  var2++;
                  break label47;
               }

               try {
                  Object[] var10000 = new Object[0];
               } catch (IllegalFormatException var5) {
                  return true;
               }
            }

            return false;
         }

         var2++;
      }
   }

   public List A() {
      if (this.sY == null) {
         throw new IllegalStateException();
      } else {
         String var1 = this.sY;
         ArrayList var2 = new ArrayList();
         IWildcardTypeManager var3 = this.pC.getWildcardTypeManager();
         boolean var4 = false;
         int var5 = 0;

         while (var5 < var1.length() - 1) {
            char var6 = var1.charAt(var5);
            if (var6 == '%') {
               if (var4) {
                  var4 = false;
                  var2.add(var3.createWithSlotcount(1));
               }

               var6 = var1.charAt(var5 + 1);
               if (var6 == '%') {
                  var5 += 2;
                  continue;
               }

               var4 = true;
            }

            if (var4) {
               switch (var6) {
                  case 'A':
                  case 'E':
                  case 'F':
                  case 'G':
                  case 'a':
                  case 'e':
                  case 'f':
                  case 'g':
                     var4 = false;
                     var2.add(var3.createWithSlotcount(2).updateGroup(IWildcardType.Group.FLOAT));
                  case 'B':
                  case 'C':
                  case 'D':
                  case 'H':
                  case 'I':
                  case 'J':
                  case 'K':
                  case 'L':
                  case 'M':
                  case 'N':
                  case 'O':
                  case 'P':
                  case 'Q':
                  case 'R':
                  case 'S':
                  case 'T':
                  case 'U':
                  case 'V':
                  case 'W':
                  case 'Y':
                  case 'Z':
                  case '[':
                  case '\\':
                  case ']':
                  case '^':
                  case '_':
                  case '`':
                  case 'b':
                  case 'h':
                  case 'j':
                  case 'k':
                  case 'l':
                  case 'm':
                  case 'q':
                  case 'r':
                  case 't':
                  case 'v':
                  case 'w':
                  default:
                     break;
                  case 'X':
                  case 'c':
                  case 'd':
                  case 'i':
                  case 'o':
                  case 'u':
                  case 'x':
                     var4 = false;
                     var2.add(var3.createWithSlotcount(1));
                     break;
                  case 'n':
                  case 'p':
                  case 's':
                     var4 = false;
                     var2.add(var3.createWithSlotcount(1).updateGroup(IWildcardType.Group.POINTER));
               }
            }

            var5++;
         }

         return var2;
      }
   }
}
