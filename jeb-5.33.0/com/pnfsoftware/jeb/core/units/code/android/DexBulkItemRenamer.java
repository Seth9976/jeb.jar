package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.units.UnitChangeEventData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.corei.parsers.dex.vi;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.bhb;
import com.pnfsoftware.jebglobal.bhc;
import java.util.Iterator;

public class DexBulkItemRenamer {
   public static final int TARGET_FLAG_CLASSES = 1;
   public static final int TARGET_FLAG_FIELDS = 2;
   public static final int TARGET_FLAG_METHODS = 4;
   public static final int TARGET_ALL = 7;
   public static final int POLICY_LEGAL = 5;
   public static final int POLICY_ASCII_PRINT = 10;
   public static final int POLICY_ASCII_ALPHANUM = 20;
   public static final int POLICY_NOTHING = 100;
   private IDexUnit dex;
   private int targets;
   private int policy;
   private String filter;
   private Integer cntCandidates;
   private int cntExamined;
   private int cntRenames;
   private int cntFailures;

   public DexBulkItemRenamer(IDexUnit var1, int var2, int var3, String var4) {
      this.dex = var1;
      this.targets = var2 & 7;
      this.policy = var3;
      if (var4 != null && Strings.isContainedIn(var4, "", "*")) {
         var4 = null;
      }

      this.filter = var4;
   }

   public int getCountOfCandidates() {
      if (this.cntCandidates == null) {
         throw new IllegalStateException("Processing has not been done yet");
      } else {
         return this.cntCandidates;
      }
   }

   public int getCountOfExamined() {
      return this.cntExamined;
   }

   public int getCountOfRenames() {
      return this.cntRenames;
   }

   public int getCountOfFailures() {
      return this.cntFailures;
   }

   public boolean process(boolean var1, boolean var2) {
      if (this.cntCandidates != null) {
         throw new IllegalStateException("Processing was already done");
      } else {
         this.cntCandidates = this.calculateCandidateCount();
         if (this.cntCandidates == 0) {
            return false;
         } else {
            Iterator var3 = this.dex.getClasses().iterator();

            while (true) {
               IDexClass var4;
               while (true) {
                  if (!var3.hasNext()) {
                     if (var2) {
                        ((vi)this.dex).pC(this.cntRenames > 0, new UnitChangeEventData(1, this));
                     }

                     return true;
                  }

                  var4 = (IDexClass)var3.next();
                  if ((this.targets & 1) == 0) {
                     break;
                  }

                  if (var1 && Thread.interrupted()) {
                     return false;
                  }

                  if (this.filter == null || var4.getSignature().startsWith(this.filter)) {
                     if (!var4.isRenamed()) {
                        this.cntExamined++;
                        String var5 = var4.getName(false);
                        String var6 = this.validate(var5, var4);
                        if (var6 != null) {
                           if (var4.setName(var6, false)) {
                              this.cntRenames++;
                           } else {
                              this.cntFailures++;
                           }
                        }
                     }
                     break;
                  }
               }

               if ((this.targets & 2) != 0) {
                  label133:
                  for (IDexField var14 : var4.getFields()) {
                     if (var1 && Thread.interrupted()) {
                        return false;
                     }

                     if ((this.filter == null || var14.getSignature().startsWith(this.filter)) && !var14.isRenamed()) {
                        this.cntExamined++;
                        String var7 = var14.getName(false);
                        String var8 = this.validate(var7, var14);
                        if (var8 != null) {
                           bhb var9 = new bhb(this.dex);

                           for (int var11 : var9.pC(var14, false)) {
                              if (!this.dex.getField(var11).isInternal()) {
                                 continue label133;
                              }
                           }

                           if (var14.setName(var8, false)) {
                              this.cntRenames++;
                           } else {
                              this.cntFailures++;
                           }
                        }
                     }
                  }
               }

               if ((this.targets & 4) != 0) {
                  label170:
                  for (IDexMethod var15 : var4.getMethods()) {
                     if (var1 && Thread.interrupted()) {
                        return false;
                     }

                     if ((this.filter == null || var15.getSignature().startsWith(this.filter)) && !var15.isRenamed()) {
                        this.cntExamined++;
                        String var16 = var15.getName(false);
                        if (!Strings.isContainedIn(var16, "<init>", "<clinit>")) {
                           String var17 = this.validate(var16, var15);
                           if (var17 != null) {
                              bhc var18 = new bhc(this.dex);

                              for (int var20 : var18.pC(var15, false)) {
                                 if (!this.dex.getMethod(var20).isInternal()) {
                                    continue label170;
                                 }
                              }

                              if (var15.setName(var17, false)) {
                                 this.cntRenames++;
                              } else {
                                 this.cntFailures++;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private int calculateCandidateCount() {
      if (this.targets == 0) {
         return 0;
      } else {
         int var1 = 0;
         Iterator var2 = this.dex.getClasses().iterator();

         while (true) {
            IDexClass var3;
            while (true) {
               if (!var2.hasNext()) {
                  return var1;
               }

               var3 = (IDexClass)var2.next();
               if ((this.targets & 1) == 0) {
                  break;
               }

               if (this.filter == null || var3.getSignature().startsWith(this.filter)) {
                  if (!var3.isRenamed()) {
                     var1++;
                  }
                  break;
               }
            }

            if ((this.targets & 2) != 0) {
               for (IDexField var5 : var3.getFields()) {
                  if ((this.filter == null || var5.getSignature().startsWith(this.filter)) && !var5.isRenamed()) {
                     var1++;
                  }
               }
            }

            if ((this.targets & 4) != 0) {
               for (IDexMethod var7 : var3.getMethods()) {
                  if ((this.filter == null || var7.getSignature().startsWith(this.filter)) && !var7.isRenamed()) {
                     var1++;
                  }
               }
            }
         }
      }
   }

   private String validate(String var1, IDexItem var2) {
      if (var1 != null && !var1.isEmpty()) {
         for (int var3 = 0; var3 < var1.length(); var3++) {
            char var4 = var1.charAt(var3);
            if (!this.isValidJavaIdentChar(var4, var3)) {
               return this.getPrefix(var2) + var2.getIndex();
            }
         }

         return null;
      } else {
         return null;
      }
   }

   private String getPrefix(IDexItem var1) {
      if (var1 instanceof IDexClass) {
         return "CLS";
      } else if (var1 instanceof IDexField) {
         return "FLD";
      } else {
         return var1 instanceof IDexMethod ? "MTH" : "ITEM";
      }
   }

   private boolean isValidJavaIdentChar(char var1, int var2) {
      switch (this.policy) {
         case 5:
            if (var2 == 0) {
               return Character.isJavaIdentifierStart(var1);
            } else {
               if (var2 > 0) {
                  return Character.isJavaIdentifierPart(var1);
               }

               return false;
            }
         case 10:
            return var1 > ' ' && var1 < 127;
         case 20:
            if (var1 >= '0' && var1 <= '9') {
               return var2 > 0;
            } else {
               if (var1 != '$' && var1 != '_' && (var1 < 'A' || var1 > 'Z') && (var1 < 'a' || var1 > 'z')) {
                  return false;
               }

               return true;
            }
         case 100:
            return false;
         default:
            return true;
      }
   }

   private void processIllegalChar(StringBuilder var1, char var2) {
      Strings.ff(var1, "u%04X", Integer.valueOf(var2));
   }
}
