package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.IrregularFlowData;
import com.pnfsoftware.jeb.core.units.code.android.dex.DexMethodHandleType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstruction;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexCallSite;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodHandle;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexPrototype;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecConversionException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDAllocObjectInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDElement;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSwitchData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTarget;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperator;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.core.units.code.java.JavaTypeUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.concurrent.Watchdog;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Integers;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class buv {
   private static final ILogger q = GlobalLog.getLogger(buv.class, Integer.MAX_VALUE);
   private boolean xK = false;
   private bud Dw;
   private btx Uv;
   private cis oW;
   private cio gO;
   private IDexUnit nf;
   private IDexMethodData gP;
   private IDexMethod za;
   private CFG lm;
   private IDFA zz;
   private Map JY;
   private Set HF = new HashSet();
   private IJavaType LK;
   private IJavaType io;
   private IJavaType qa;
   private buv.eo Hk = new buv.eo();
   private int Me;
   private List PV;
   private Set oQ = new HashSet();
   private Map xW;
   private Map KT;
   private boolean Gf;
   private List Ef;
   private Set cC;
   private Set sH = new TreeSet();
   private Set CE = new TreeSet();
   private Map wF;
   private bix If;
   private List Dz;
   private Watchdog mI;
   private static String jq = "_zthdgtehsuiesuuwthheyshqoplejsmnxyehhte_";
   private int ui = 0;
   private int TX = 0;
   private static Set EB;

   public buv(bud dw) {
      if (dw == null) {
         throw new IllegalArgumentException();
      } else {
         this.Dw = dw;
         this.Uv = (btx)dw.getGlobalContext();
         this.nf = dw.getDex();
         this.za = dw.getMethod();
         this.mI = dw.getWatchdog();
         this.gP = this.za.getData();
         this.oW = this.Uv.xK();
         this.gO = this.Uv.Dw();
         this.LK = this.oW.createType("Ljava/lang/Object;");
         this.io = this.oW.createType("Ljava/lang/Class;");
         this.qa = this.oW.createType("Ljava/lang/String;");
         this.lm = this.gP.getCodeItem().getControlFlowGraph();
         this.zz = this.lm.createDataFlowAnalysisHelperObject();
         this.JY = this.lm.getAddressBlockMap();
         this.ui = 0;
         String name = this.za.getName(false);
         int index;
         if ((index = name.indexOf(jq)) >= 0) {
            String substring = name.substring(index + jq.length());
            if (substring.startsWith("crash")) {
               int stringToInt = Conversion.stringToInt(substring.substring(5), 1);
               if (stringToInt == 1 || stringToInt == 2) {
                  this.ui = stringToInt;
               }
            } else if (substring.startsWith("stall")) {
               String substring2 = substring.substring(5);
               this.ui = 3;
               this.TX = Conversion.stringToInt(substring2, 0);
            } else if (substring.startsWith("loop")) {
               String substring3 = substring.substring(4);
               this.ui = 4;
               this.TX = Conversion.stringToInt(substring3, 0);
            }
         }
      }
   }

   public void q(boolean b, boolean b2, boolean gf) throws DexDecConversionException {
      if (this.xK) {
         throw new IllegalStateException("Already transformed");
      } else {
         this.Gf = gf;
         if (gf) {
            this.nf.addMethod("Ljeb/synthetic/Objects;->alloc(Ljava/lang/Class;)Ljava/lang/Object;");
         }

         this.Me = 0;
         this.xW = new HashMap();
         this.KT = new HashMap();
         this.Ef = new ArrayList();
         this.cC = new HashSet();
         this.wF = new HashMap();
         this.If = new bix(this.gP.getCodeItem().getExceptionItems());
         if (this.ui == 3 || this.ui == 4) {
            boolean b3 = this.ui == 3;
            this.ui = 0;
            int n = this.TX == 0 ? Integer.MAX_VALUE : this.TX * 10;

            for (int i = 0; i < n; i++) {
               try {
                  Thread.sleep(100L);
                  if (b3) {
                     Watchdog.verify(this.mI);
                  }
               } catch (InterruptedException var18) {
                  if (b3) {
                     Watchdog.reportInterruption(var18);
                  } else {
                     Thread.currentThread().interrupt();
                  }
               }
            }
         }

         this.PV = new ArrayList();

         for (int j = 0; j < this.lm.size(); j++) {
            this.PV.addAll(this.RF(j));
         }

         if (this.PV instanceof ArrayList list) {
            list.trimToSize();
         }

         int n2 = (int)this.lm.get(this.lm.size() - 1).getEndAddress();
         this.xW.put(n2, this.Me);
         this.KT.put(this.Me, n2);

         for (IDTarget idTarget : this.Hk.q()) {
            int offset = idTarget.getOffset();
            Integer n3 = (Integer)this.xW.get(offset);
            if (n3 == null) {
               throw new RuntimeException(Strings.ff("Cannot map Dalvik offset 0x%X to generated IR", offset));
            }

            idTarget.setOffset(n3);
         }

         Iterator iterator2 = this.cC.iterator();

         while (iterator2.hasNext()) {
            Integer n4 = (Integer)this.xW.get((int)((IDalvikInstruction)iterator2.next()).getOffset());
            if (n4 != null && n4 < this.PV.size()) {
               IDInstruction idInstruction = (IDInstruction)this.PV.get(n4);
               if (idInstruction.getOffset() == n4.intValue() && idInstruction.getOpcode() == DOpcodeType.IR_ASSIGN) {
                  idInstruction.setAssignSource(this.Uv.createNull());
               }
            }
         }

         this.cC.clear();
         if (!gf && !this.Ef.isEmpty()) {
            q.warn(S.L("The method cannot be properly decompiled, new-instance instructions remain unmatched"));

            for (IDalvikInstruction dalvikInstruction : this.Ef) {
               q.warn("%04Xh: %s (%s)", dalvikInstruction.getOffset(), dalvikInstruction.format(null), S.L("unmatched"));
            }
         }

         if (!this.CE.isEmpty()) {
            com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(
               new RuntimeException("Failed type determination for fill-array-data instructions: " + Integers.formatHexIntegerCollection(this.CE)),
               this.za.getSignature(false)
            );
         }

         this.Dz = new ArrayList();
         if (b) {
            for (BasicBlock basicBlock : this.lm) {
               if (basicBlock.irroutsize() != 0) {
                  int n5 = (int)basicBlock.get(0).getOffset();
                  Integer n6 = (Integer)this.xW.get(n5);
                  if (n6 == null) {
                     throw new RuntimeException(Strings.ff("Failed to convert Dalvik try-start offset: 0x%X", n5));
                  }

                  int intValue = n6;
                  int n7 = (int)basicBlock.get(basicBlock.size() - 1).getOffset();
                  Integer n8 = (Integer)this.xW.get(n7);
                  if (n8 == null) {
                     throw new RuntimeException(Strings.ff("Failed to convert Dalvik try-last offset: 0x%X", n7));
                  }

                  int intValue2 = n8;
                  int n9 = 1;

                  while (true) {
                     Integer n10 = (Integer)this.KT.get(n8 + n9);
                     if (n10 == null || n10 != n7) {
                        Iterator iterator5 = basicBlock.getIrregularOutputBlocks().iterator();

                        while (iterator5.hasNext()) {
                           int n11 = (int)((BasicBlock)iterator5.next()).get(0).getOffset();
                           Integer n12 = (Integer)this.xW.get(n11);
                           if (n12 == null) {
                              throw new RuntimeException(Strings.ff("Failed to convert Dalvik try-handler offset: 0x%X", n11));
                           }

                           this.Dz.add(new IrregularFlowData(intValue, intValue2, n12.intValue()));
                        }
                        break;
                     }

                     intValue2++;
                     n9++;
                  }
               }
            }
         }

         this.zz = null;
         this.xK = true;
      }
   }

   public List q() {
      if (!this.xK) {
         throw new RuntimeException();
      } else {
         return this.PV;
      }
   }

   public List RF() {
      if (!this.xK) {
         throw new RuntimeException();
      } else {
         return this.Dz;
      }
   }

   public Map xK() {
      if (!this.xK) {
         throw new IllegalStateException();
      } else {
         return this.xW;
      }
   }

   public Set Dw() {
      return Collections.unmodifiableSet(this.HF);
   }

   public Map Uv() {
      if (!this.xK) {
         throw new IllegalStateException();
      } else {
         return this.KT;
      }
   }

   private IDVar q(int n, IJavaType javaType) {
      return this.Dw.createVar(DUtil.createRegisterVarId(n, javaType.isDoubleSlot()));
   }

   private List RF(int n) throws DexDecConversionException {
      BasicBlock value = this.lm.get(n);
      List instructions = value.getInstructions();
      ArrayList list = new ArrayList();
      int i = 0;
      IDalvikInstruction dalvikInstruction = null;
      List list2 = null;
      IDalvikInstruction[] array = new IDalvikInstruction[]{null};
      int[] array2 = new int[]{0};
      HashSet set = new HashSet();
      HashSet set2 = new HashSet();

      while (i < instructions.size()) {
         IDalvikInstruction dalvikInstruction2 = (IDalvikInstruction)instructions.get(i);
         int opcode;
         if (this.oQ.contains(dalvikInstruction2.getOffset())) {
            opcode = 0;
         } else {
            opcode = dalvikInstruction2.getOpcode();
         }

         IDInstruction idInstruction = null;
         int parameterCount = dalvikInstruction2.getParameterCount();
         int j = -1;
         if (parameterCount >= 1 && dalvikInstruction2.getParameterType(0) == 0) {
            j = (int)dalvikInstruction2.getParameterValue(0);
         }

         int n2 = -1;
         if (parameterCount >= 2 && dalvikInstruction2.getParameterType(1) == 0) {
            n2 = (int)dalvikInstruction2.getParameterValue(1);
         }

         int n3 = -1;
         if (parameterCount >= 3 && dalvikInstruction2.getParameterType(2) == 0) {
            n3 = (int)dalvikInstruction2.getParameterValue(2);
         }

         if (this.ui > 0) {
            int ui = this.ui;
            this.ui = 0;
            if (ui == 1) {
               throw new RuntimeException("BOOH!");
            }

            if (ui == 2) {
               throw new DexDecConversionException(dalvikInstruction2);
            }
         }

         switch (opcode) {
            case 0:
               idInstruction = this.Dw.createNop();
               break;
            case 1:
            case 2:
            case 3:
               idInstruction = this.Dw.q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.za), this.q(n2, this.oW.za));
               break;
            case 4:
            case 5:
            case 6:
               idInstruction = this.Dw.q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.lm), this.q(n2, this.oW.lm));
               break;
            case 7:
            case 8:
            case 9:
               idInstruction = this.Dw.q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.JY), this.q(n2, this.oW.JY));
            case 10:
            case 11:
            case 12:
               break;
            case 13:
               if (i != 0) {
                  for (int k = 0; k < i; k++) {
                     if (((IDalvikInstruction)instructions.get(k)).getOpcode() != 0) {
                        throw new RuntimeException("Illegal move-exception position");
                     }
                  }
               }

               this.wF.put(this.Me, j);
               int rf = this.If.RF((int)value.getAddress());
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_STORE_EXCEPTION, this.q(j, this.oW.q(rf < 0 ? "Ljava/lang/Throwable;" : this.nf.getType(rf).getSignature(false))), null);
               break;
            case 14:
               idInstruction = this.Dw.q(DOpcodeType.IR_RETURN, null, null);
               break;
            case 15:
               idInstruction = this.Dw.q(DOpcodeType.IR_RETURN, null, this.q(j, this.oW.za));
               break;
            case 16:
               idInstruction = this.Dw.q(DOpcodeType.IR_RETURN, null, this.q(j, this.oW.lm));
               break;
            case 17:
               idInstruction = this.Dw.q(DOpcodeType.IR_RETURN, null, this.q(j, this.oW.JY));
               break;
            case 18:
            case 19:
            case 20:
            case 21:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.za), this.Uv.createConstant((int)dalvikInstruction2.getParameterValue(1), this.oW.za));
               break;
            case 22:
            case 23:
            case 24:
            case 25:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.lm), this.Uv.createConstant(dalvikInstruction2.getParameterValue(1), this.oW.lm));
               break;
            case 26:
            case 27:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_ASSIGN, this.q(j, this.qa), this.Uv.createString(this.Uv.createIndex((int)dalvikInstruction2.getParameterValue(1))));
               break;
            case 28:
               idInstruction = this.Dw
                  .q(
                     DOpcodeType.IR_ASSIGN,
                     this.q(j, this.io),
                     this.Uv.createStaticField(null, this.io, this.nf.getType((int)dalvikInstruction2.getParameterValue(1)).getSignature(false), "class")
                  );
               break;
            case 29:
               idInstruction = this.Dw.q(DOpcodeType.IR_MONITOR_ENTER, null, this.q(j, this.oW.JY));
               break;
            case 30:
               idInstruction = this.Dw.q(DOpcodeType.IR_MONITOR_EXIT, null, this.q(j, this.oW.JY));
               break;
            case 31:
            case 511:
               IJavaType type = this.oW.parseType(this.nf.getType((int)dalvikInstruction2.getParameterValue(1)).getSignature(false));
               idInstruction = this.Dw.q(DOpcodeType.IR_ASSIGN, this.q(j, type), this.Uv.createCastOperation(type, this.q(j, this.oW.JY)));
               break;
            case 32:
            case 767:
               int n22 = (int)dalvikInstruction2.getParameterValue(2);
               idInstruction = this.Dw
                  .q(
                     DOpcodeType.IR_ASSIGN,
                     this.q(j, this.oW.RF),
                     this.Uv
                        .createOperation(
                           this.oW.RF,
                           this.q(n2, this.oW.JY),
                           this.xK(opcode),
                           this.Uv.createReferenceType(this.Uv.createIndex(n22), this.oW.parseType(this.nf.getType(n22).getSignature(false)))
                        )
                  );
               break;
            case 33:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.oW), this.Uv.createInstanceField(this.q(n2, this.oW.JY), null, this.oW.oW, "length"));
               break;
            case 34:
            case 1023:
               Object o;
               if (this.Gf) {
                  o = this.Uv.createAllocObjectInfo(this.oW.parseType(this.nf.getType((int)dalvikInstruction2.getParameterValue(1)).getSignature(false)));
               } else {
                  this.Ef.add(dalvikInstruction2);
                  o = this.Uv.createConstant(0L, this.oW.JY);
               }

               idInstruction = this.Dw.q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.JY), (IDElement)o);
               break;
            case 35:
            case 1279:
               IJavaType type2 = this.oW.parseType(this.nf.getType((int)dalvikInstruction2.getParameterValue(2)).getSignature(false));
               if (type2.getDimensions() <= 0) {
                  throw new RuntimeException("Expected an array type for new-array");
               }

               List list3 = null;
               IDalvikInstruction dalvikInstruction4 = null;
               if (i + 1 < value.size()) {
                  IDalvikInstruction dalvikInstruction5 = (IDalvikInstruction)value.get(i + 1);
                  if (dalvikInstruction5.getOpcode() == 38 && (int)dalvikInstruction5.getParameterValue(0) == j) {
                     dalvikInstruction4 = dalvikInstruction5;
                  }
               }

               if (dalvikInstruction4 != null) {
                  list3 = new ArrayList();
                  byte[] array6 = new byte[8];
                  ByteBuffer wrap = ByteBuffer.wrap(array6);
                  wrap.order(ByteOrder.LITTLE_ENDIAN);
                  IJavaType arrayElementType = type2.getArrayElementType();
                  if (!arrayElementType.isPrimitive()) {
                     throw new RuntimeException("Expected a primitive array element, got: " + arrayElementType);
                  }

                  byte[][] elements = dalvikInstruction4.getArrayData().getElements();

                  for (int n23 = 0; n23 < elements.length; n23++) {
                     for (int n24 = 0; n24 < elements[n23].length; n24++) {
                        array6[n24] = elements[n23][n24];
                     }

                     list3.add(this.Uv.createConstant(wrap.getLong(0), arrayElementType));
                  }

                  this.sH.add((int)dalvikInstruction4.getOffset());
               }

               idInstruction = this.Dw.q(DOpcodeType.IR_ASSIGN, this.q(j, type2), this.Uv.createNewArrayInfo(type2, this.q(n2, this.oW.zz), list3));
               break;
            case 36:
            case 37:
            case 1535:
               IJavaType type3 = this.oW.parseType(this.nf.getType((int)dalvikInstruction2.getParameterValue(0)).getSignature(false));
               IDVar q7 = this.q(n, i, type3, array);
               if (q7 != null) {
                  dalvikInstruction = array[0];
               }

               int[] array8;
               if (opcode == 36) {
                  array8 = new int[dalvikInstruction2.getParameterCount() - 1];

                  for (int n28 = 1; n28 < dalvikInstruction2.getParameterCount(); n28++) {
                     array8[n28 - 1] = (int)dalvikInstruction2.getParameterValue(n28);
                  }
               } else {
                  long parameterValue2 = dalvikInstruction2.getParameterValue(1);
                  int n29 = (int)(parameterValue2 & -1L);
                  int n30 = (int)(parameterValue2 >> 32 & -1L);
                  array8 = new int[n30 - n29 + 1];
                  int n31 = 0;
                  int n32 = n29;

                  while (n32 <= n30) {
                     array8[n31++] = n32++;
                  }
               }

               IJavaType arrayTypeDimensionBelow2 = type3.getArrayTypeDimensionBelow();
               ArrayList list4 = new ArrayList(array8.length);

               for (int n33 = 0; n33 < array8.length; n33++) {
                  list4.add(this.q(array8[n33], arrayTypeDimensionBelow2));
               }

               IDNewArrayInfo newArrayInfo = this.Uv.createNewArrayInfo(type3, this.Uv.createConstant(list4.size(), this.oW.oW), list4);
               if (q7 != null) {
                  idInstruction = this.Dw.q(DOpcodeType.IR_ASSIGN, q7, newArrayInfo);
               } else {
                  idInstruction = this.Dw.q(DOpcodeType.IR_INVOKE, null, newArrayInfo);
               }
               break;
            case 38:
               int n25 = (int)dalvikInstruction2.getOffset();
               if (this.sH.contains(n25)) {
                  idInstruction = this.Dw.createNop();
               } else {
                  IJavaType javaType2 = new buv.CU().q(dalvikInstruction2);
                  if (javaType2 == null) {
                     this.CE.add(n25);
                     byte[][] elements2 = dalvikInstruction2.getArrayData().getElements();
                     if (elements2.length >= 1) {
                        String s2 = null;
                        int length = elements2[0].length;
                        if (length == 1) {
                           s2 = "[B";
                        } else if (length == 2) {
                           s2 = "[S";
                        } else if (length == 4) {
                           s2 = "[I";
                        } else if (length == 8) {
                           s2 = "[J";
                        }

                        if (s2 != null) {
                           javaType2 = this.oW.parseType(s2);
                        }
                     }
                  }

                  if (javaType2 == null) {
                     idInstruction = this.Dw.createNop();
                  } else {
                     IJavaType arrayTypeDimensionBelow = javaType2.getArrayTypeDimensionBelow();
                     byte[] array7 = new byte[8];
                     ByteBuffer wrap2 = ByteBuffer.wrap(array7);
                     wrap2.order(ByteOrder.LITTLE_ENDIAN);
                     byte[][] elements3 = dalvikInstruction2.getArrayData().getElements();
                     list2 = new ArrayList(elements3.length);

                     for (int n26 = 0; n26 < elements3.length; n26++) {
                        for (int n27 = 0; n27 < elements3[n26].length; n27++) {
                           array7[n27] = elements3[n26][n27];
                        }

                        IDInstruction q6 = this.Dw
                           .q(
                              DOpcodeType.IR_ASSIGN,
                              this.Uv.createArrayElt(this.q(j, javaType2), this.Uv.createConstant(n26, this.oW.oW), arrayTypeDimensionBelow),
                              this.Uv.createConstant(wrap2.getLong(0), arrayTypeDimensionBelow)
                           );
                        if (idInstruction == null) {
                           idInstruction = q6;
                        } else {
                           list2.add(q6);
                        }
                     }
                  }
               }
               break;
            case 39:
               idInstruction = this.Dw.q(DOpcodeType.IR_THROW, null, this.q(j, this.oW.JY));
               break;
            case 40:
            case 41:
            case 42:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_JUMP, this.Hk.q((int)dalvikInstruction2.getOffset() + 2 * (int)dalvikInstruction2.getParameterValue(0)), null);
               break;
            case 43:
            case 44:
               int n34 = (int)dalvikInstruction2.getOffset() + dalvikInstruction2.getSize();
               IDSwitchData switchData = this.Uv.createSwitchData();

               for (int[] array9 : dalvikInstruction2.getSwitchData().getElements()) {
                  int n36 = (int)dalvikInstruction2.getOffset() + 2 * array9[1];
                  if (n36 != n34) {
                     switchData.addCase(array9[0], this.Hk.q(n36), false);
                  }
               }

               idInstruction = this.Dw.q(DOpcodeType.IR_SWITCH, switchData, this.q(j, this.oW.zz));
               break;
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
               int opcode2 = -1;
               if (i + 1 < instructions.size()) {
                  dalvikInstruction = (IDalvikInstruction)instructions.get(i + 1);
                  opcode2 = dalvikInstruction.getOpcode();
                  if (opcode2 >= 56 && opcode2 <= 61) {
                     if (!this.zz.checkSingleUse(dalvikInstruction2.getOffset(), j, dalvikInstruction.getOffset())) {
                        dalvikInstruction = null;
                     }
                  } else {
                     dalvikInstruction = null;
                  }
               }

               if (dalvikInstruction != null) {
                  int n37 = (int)dalvikInstruction.getOffset() + 2 * (int)dalvikInstruction.getParameterValue(1);
                  if (n37 != dalvikInstruction.getOffset() + dalvikInstruction.getSize()) {
                     IJavaType dw = this.Dw(opcode);
                     IDOperation idOperation;
                     if ((opcode == 47 || opcode == 45) && (opcode2 == 58 || opcode2 == 61)
                        || (opcode == 48 || opcode == 46) && (opcode2 == 60 || opcode2 == 59)) {
                        IJavaOperator javaOperator = this.xK(opcode2);
                        if (opcode2 == 58) {
                           javaOperator = this.gO.Gf;
                        }

                        if (opcode2 == 61) {
                           javaOperator = this.gO.Ef;
                        }

                        if (opcode2 == 60) {
                           javaOperator = this.gO.cC;
                        }

                        if (opcode2 == 59) {
                           javaOperator = this.gO.KT;
                        }

                        idOperation = this.Uv
                           .createPredicate(JavaOperatorType.LOG_NOT, null, this.Uv.createPredicate(this.q(n2, dw), javaOperator, this.q(n3, dw)));
                     } else {
                        idOperation = this.Uv.createPredicate(this.q(n2, dw), this.xK(opcode2), this.q(n3, dw));
                     }

                     idInstruction = this.Dw.q(DOpcodeType.IR_JCOND, this.Hk.q(n37), idOperation);
                  }
               } else {
                  IDexMethod dexMethod = null;
                  if (opcode == 45 || opcode == 46) {
                     dexMethod = this.nf.addMethod("Ljava/lang/Float;->compare(FF)I");
                  } else if (opcode == 47 || opcode == 48) {
                     dexMethod = this.nf.addMethod("Ljava/lang/Double;->compare(DD)I");
                  } else if (opcode == 49) {
                     dexMethod = this.nf.addMethod("Ljava/lang/Long;->compare(JJ)I");
                  }

                  if (dexMethod == null) {
                     throw new RuntimeException(Strings.ff("Need a compare() method - it seems none was generate for opcode 0x%X", opcode));
                  }

                  IJavaType[] fullPrototype2 = JavaTypeUtil.parseFullPrototype(dexMethod.getPrototype(), this.oW);
                  idInstruction = this.Dw
                     .q(
                        DOpcodeType.IR_ASSIGN,
                        this.q(j, fullPrototype2[0]),
                        this.Uv
                           .createCallInfo(
                              this.Uv.createIndex(dexMethod.getIndex()),
                              new IDExpression[]{this.q(n2, fullPrototype2[1]), this.q(n3, fullPrototype2[2])},
                              fullPrototype2[0],
                              dexMethod.getSignature(false),
                              DInvokeType.STATIC
                           )
                     );
               }
               break;
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
               int n38 = (int)dalvikInstruction2.getOffset() + dalvikInstruction2.getSize();
               int n39 = (int)dalvikInstruction2.getOffset() + 2 * (int)dalvikInstruction2.getParameterValue(2);
               if (n39 != n38) {
                  idInstruction = this.Dw
                     .q(DOpcodeType.IR_JCOND, this.Hk.q(n39), this.Uv.createPredicate(this.q(j, this.oW.za), this.xK(opcode), this.q(n2, this.oW.za)));
               } else {
                  idInstruction = this.Dw.q(DOpcodeType.IR_JUMP, this.Hk.q(n38), null);
               }
               break;
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
               int n40 = (int)dalvikInstruction2.getOffset() + dalvikInstruction2.getSize();
               int n41 = (int)dalvikInstruction2.getOffset() + 2 * (int)dalvikInstruction2.getParameterValue(1);
               if (n41 != n40) {
                  idInstruction = this.Dw
                     .q(
                        DOpcodeType.IR_JCOND,
                        this.Hk.q(n41),
                        this.Uv.createPredicate(this.q(j, this.oW.za), this.xK(opcode), this.Uv.createConstant(0L, this.oW.za))
                     );
               } else {
                  idInstruction = this.Dw.q(DOpcodeType.IR_JUMP, this.Hk.q(n40), null);
               }
               break;
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
               IJavaType dw2 = this.Dw(opcode);
               idInstruction = this.Dw
                  .q(
                     DOpcodeType.IR_ASSIGN,
                     this.q(j, (IJavaType)(dw2.isSmallInt() ? this.oW.zz : dw2)),
                     this.Uv.createArrayElt(this.q(n2, this.oW.JY), this.q(n3, this.oW.zz), dw2)
                  );
               break;
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
               IJavaType dw3 = this.Dw(opcode);
               idInstruction = this.Dw.q(DOpcodeType.IR_ASSIGN, this.Uv.createArrayElt(this.q(n2, this.oW.JY), this.q(n3, this.oW.zz), dw3), this.q(j, dw3));
               break;
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 1791:
            case 2047:
            case 2303:
            case 2559:
            case 2815:
            case 3071:
            case 3327:
            case 3583:
            case 3839:
            case 4095:
            case 4351:
            case 4607:
            case 4863:
            case 5119:
               int n42 = (int)dalvikInstruction2.getParameterValue(2);
               IDexField field = this.nf.getField(n42);
               String name2 = field.getName(false);
               IJavaType type4 = this.oW.parseType(field.getFieldTypeSignature(false));
               IDVar q8 = this.q(j, type4);
               IDInstanceField instanceField = this.Uv
                  .createInstanceField(this.q(n2, this.oW.q(field.getClassTypeSignature(false))), this.Uv.createIndex(n42), type4, name2);
               if ((opcode < 82 || opcode > 88) && (opcode < 1791 || opcode > 3327)) {
                  idInstruction = this.Dw.q(DOpcodeType.IR_ASSIGN, instanceField, q8);
               } else {
                  idInstruction = this.Dw.q(DOpcodeType.IR_ASSIGN, q8, instanceField);
               }
               break;
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            case 5375:
            case 5631:
            case 5887:
            case 6143:
            case 6399:
            case 6655:
            case 6911:
            case 7167:
            case 7423:
            case 7679:
            case 7935:
            case 8191:
            case 8447:
            case 8703:
               int n43 = (int)dalvikInstruction2.getParameterValue(1);
               IDexField field2 = this.nf.getField(n43);
               IJavaType type5 = this.oW.parseType(field2.getFieldTypeSignature(false));
               IDVar q9 = this.q(j, type5);
               IDStaticField staticField = this.Uv
                  .createStaticField(this.Uv.createIndex(n43), type5, field2.getClassTypeSignature(false), field2.getName(false));
               if ((opcode < 96 || opcode > 102) && (opcode < 5375 || opcode > 6911)) {
                  idInstruction = this.Dw.q(DOpcodeType.IR_ASSIGN, staticField, q9);
               } else {
                  idInstruction = this.Dw.q(DOpcodeType.IR_ASSIGN, q9, staticField);
               }
               break;
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 250:
            case 251:
            case 252:
            case 253:
            case 8959:
            case 9215:
            case 9471:
            case 9727:
            case 9983:
               DInvokeType dInvokeType;
               if (opcode == 110 || opcode == 116 || opcode == 8959) {
                  dInvokeType = DInvokeType.VIRTUAL;
               } else if (opcode == 111 || opcode == 117 || opcode == 9215) {
                  dInvokeType = DInvokeType.SUPER;
               } else if (opcode == 112 || opcode == 118 || opcode == 9471) {
                  dInvokeType = DInvokeType.DIRECT;
               } else if (opcode == 113 || opcode == 119 || opcode == 9727) {
                  dInvokeType = DInvokeType.STATIC;
               } else if (opcode == 114 || opcode == 120 || opcode == 9983) {
                  dInvokeType = DInvokeType.INTERFACE;
               } else if (opcode != 250 && opcode != 251) {
                  if (opcode != 252 && opcode != 253) {
                     throw new RuntimeException("Unsupported invocation type");
                  }

                  dInvokeType = DInvokeType.CUSTOM;
               } else {
                  dInvokeType = DInvokeType.POLYMORPHIC;
               }

               int n4 = -1;
               IDexCallSite callSite = null;
               if (dInvokeType != DInvokeType.CUSTOM) {
                  n4 = (int)dalvikInstruction2.getParameterValue(0);
               } else {
                  int n5 = (int)dalvikInstruction2.getParameterValue(0);
                  callSite = this.nf.getCallSite(n5);
                  IDexMethodHandle linkerMethodHandle = callSite.getLinkerMethodHandle();
                  if (linkerMethodHandle.getMethodHandleType().isMethodInvoker()) {
                     if ("Ljava/lang/invoke/LambdaMetafactory;->metafactory(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                        .equals(this.nf.getMethod(linkerMethodHandle.getFieldOrMethodIndex()).getSignature())) {
                        IDexMethodHandle methodHandle = this.nf.getMethodHandle(callSite.getCallSiteValue(4).getMethodHandleIndex());
                        if (methodHandle.getMethodHandleType().isMethodInvoker()) {
                           n4 = methodHandle.getFieldOrMethodIndex();
                           dInvokeType = DInvokeType.LAMBDA;
                        }
                     } else if (linkerMethodHandle.getMethodHandleType() == DexMethodHandleType.INVOKE_STATIC) {
                        String value2 = callSite.getDynamicMethodName().getValue(false);
                        String generate = callSite.getDynamicMethodPrototype().generate(false);
                        String name = null;
                        if (callSite.getLinkerMethodHandle().getMethodHandleType() == DexMethodHandleType.INVOKE_STATIC) {
                           name = this.nf.getMethod(callSite.getLinkerMethodHandle().getFieldOrMethodIndex()).getName(false);
                        }

                        n4 = this.nf
                           .addMethod(
                              "Ljeb/synthetic/InvokeCustoms;", "CallSite" + n5 + "_`" + value2 + "`" + (name == null ? "" : "_from_`" + name + "`"), generate
                           )
                           .getIndex();
                        dInvokeType = DInvokeType.STATIC;
                     }
                  }
               }

               if (n4 < 0) {
                  throw new RuntimeException("Unsupported conversion for some invoke-custom");
               }

               IDexMethod method = this.nf.getMethod(n4);
               String classTypeSignature = method.getClassTypeSignature(false);
               int n6 = 1;
               IDexPrototype dexPrototype = method.getPrototype();
               if (dInvokeType == DInvokeType.POLYMORPHIC) {
                  int n7 = (int)dalvikInstruction2.getParameterValue(1);
                  n6 = 2;
                  dexPrototype = this.nf.getPrototype(n7);
               } else if (dInvokeType == DInvokeType.LAMBDA) {
                  dexPrototype = callSite.getDynamicMethodPrototype();
               }

               IJavaType[] fullPrototype = JavaTypeUtil.parseFullPrototype(dexPrototype, this.oW);
               IJavaType zz = fullPrototype[0];
               if (zz == this.oW.xK || zz == this.oW.Dw || zz == this.oW.Uv) {
                  zz = this.oW.zz;
               }

               IDVar q = this.q(n, i, zz, array, array2);
               if (q != null && array2[0] == 0) {
                  dalvikInstruction = array[0];
               }

               boolean b = dInvokeType == DInvokeType.STATIC || dInvokeType == DInvokeType.LAMBDA;
               int l = 0;
               int n8 = 0;
               if (!b) {
                  l++;
                  n8++;
               }

               for (int n9 = 1; n9 < fullPrototype.length; n9++) {
                  if (fullPrototype[n9] == this.oW.gO || fullPrototype[n9] == this.oW.gP) {
                     l++;
                  }

                  l++;
                  n8++;
               }

               int[] array3;
               if (opcode != 110 && opcode != 111 && opcode != 112 && opcode != 113 && opcode != 114 && opcode != 250 && opcode != 252) {
                  long parameterValue = dalvikInstruction2.getParameterValue(n6);
                  int n12 = (int)(parameterValue & -1L);
                  int n13 = (int)(parameterValue >> 32 & -1L);
                  array3 = new int[n13 - n12 + 1];
                  int n14 = 0;
                  int n15 = n12;

                  while (n15 <= n13) {
                     array3[n14++] = n15++;
                  }
               } else {
                  array3 = new int[dalvikInstruction2.getParameterCount() - n6];
                  int n10 = 0;

                  for (int n11 = n6; n11 < dalvikInstruction2.getParameterCount(); n11++) {
                     array3[n10++] = (int)dalvikInstruction2.getParameterValue(n11);
                  }
               }

               if (l != array3.length) {
                  throw new DexDecConversionException(dalvikInstruction2, Strings.ff("Unexpected registers count: %d, %d", l, array3.length));
               }

               IDExpression[] array4 = new IDExpression[n8];
               int n16 = 0;
               int n17 = 0;
               if (!b) {
                  array4[n16++] = this.q(array3[n17], this.oW.parseType(classTypeSignature));
                  n17++;
               }

               for (int n18 = 1; n16 < array4.length; n18++) {
                  IJavaType zz2 = fullPrototype[n18];
                  if (zz2 == this.oW.oW || zz2 == this.oW.Uv || zz2 == this.oW.Dw || zz2 == this.oW.xK || zz2 == this.oW.RF) {
                     zz2 = this.oW.zz;
                  }

                  array4[n16++] = this.q(array3[n17], zz2);
                  if (zz2 == this.oW.gO || zz2 == this.oW.gP) {
                     if (array3[n17 + 1] != array3[n17] + 1) {
                        throw new RuntimeException();
                     }

                     n17++;
                  }

                  n17++;
               }

               if (!b && method.getName(false).equals("<init>")) {
                  set.clear();
                  set2.clear();
                  IDalvikInstruction q2 = null;
                  if (this.Gf) {
                     if (i >= 1) {
                        IDalvikInstruction dalvikInstruction3 = (IDalvikInstruction)value.get(i - 1);
                        IDInstruction idInstruction2;
                        if ((dalvikInstruction3.getOpcode() == 34 || dalvikInstruction3.getOpcode() == 1023)
                           && (int)dalvikInstruction3.getParameterValue(0) == array3[0]
                           && !list.isEmpty()
                           && (idInstruction2 = (IDInstruction)list.get(list.size() - 1)).isAssignToVar()
                           && idInstruction2.getAssignSource() instanceof IDAllocObjectInfo) {
                           q2 = dalvikInstruction3;
                           idInstruction2.setAssignSource(this.Uv.createNull());
                        }
                     }
                  } else {
                     q2 = this.q(dalvikInstruction2, array3[0], set, set2, value, i);
                  }

                  if (q2 != null) {
                     int n19 = array3[0];
                     String signature = this.nf.getType((int)q2.getParameterValue(1)).getSignature(false);
                     boolean b2 = false;
                     if (!signature.equals(classTypeSignature)) {
                        b2 = true;
                     }

                     this.cC.addAll(set2);
                     this.Ef.remove(q2);
                     IJavaType q3 = this.oW.q(signature);
                     IJavaType javaType = b2 ? this.oW.q(classTypeSignature) : q3;
                     IDExpression[] array5 = new IDExpression[array4.length - 1];

                     for (int n20 = 1; n20 < array4.length; n20++) {
                        array5[n20 - 1] = array4[n20];
                     }

                     idInstruction = this.Dw
                        .q(
                           DOpcodeType.IR_ASSIGN,
                           this.q(n19, q3),
                           this.Uv
                              .createNewInfo(
                                 q3, javaType, this.Uv.createIndex((int)dalvikInstruction2.getParameterValue(0)), array5, method.getSignature(false)
                              )
                        );
                     if (!set.isEmpty()) {
                        list2 = new ArrayList(set.size());

                        for (int intValue : set) {
                           if (intValue != n19) {
                              list2.add(this.Dw.q(DOpcodeType.IR_ASSIGN, this.q(intValue, q3), this.q(n19, q3)));
                           }
                        }
                     }
                  }
               }

               if (idInstruction == null) {
                  IDCallInfo callInfo = this.Uv.createCallInfo(this.Uv.createIndex(n4), array4, zz, method.getSignature(false), dInvokeType);
                  if (q != null) {
                     idInstruction = this.Dw.q(DOpcodeType.IR_ASSIGN, q, callInfo);
                  } else {
                     idInstruction = this.Dw.q(DOpcodeType.IR_INVOKE, null, callInfo);
                  }
               }
               break;
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
               idInstruction = this.Dw
                  .q(
                     DOpcodeType.IR_ASSIGN,
                     this.q(j, this.Dw(opcode)),
                     this.Uv.createOperation(this.Dw(opcode), null, this.xK(opcode), this.q(n2, this.Dw(opcode)))
                  );
               break;
            case 129:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.gO), this.Uv.createOperation(this.oW.gO, null, this.xK(opcode), this.q(n2, this.oW.zz)));
               break;
            case 130:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.nf), this.Uv.createOperation(this.oW.nf, null, this.xK(opcode), this.q(n2, this.oW.zz)));
               break;
            case 131:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.gP), this.Uv.createOperation(this.oW.gP, null, this.xK(opcode), this.q(n2, this.oW.zz)));
               break;
            case 132:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.oW), this.Uv.createOperation(this.oW.oW, null, this.xK(opcode), this.q(n2, this.oW.gO)));
               break;
            case 133:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.nf), this.Uv.createOperation(this.oW.nf, null, this.xK(opcode), this.q(n2, this.oW.gO)));
               break;
            case 134:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.gP), this.Uv.createOperation(this.oW.gP, null, this.xK(opcode), this.q(n2, this.oW.gO)));
               break;
            case 135:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.oW), this.Uv.createOperation(this.oW.oW, null, this.xK(opcode), this.q(n2, this.oW.nf)));
               break;
            case 136:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.gO), this.Uv.createOperation(this.oW.gO, null, this.xK(opcode), this.q(n2, this.oW.nf)));
               break;
            case 137:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.gP), this.Uv.createOperation(this.oW.gP, null, this.xK(opcode), this.q(n2, this.oW.nf)));
               break;
            case 138:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.oW), this.Uv.createOperation(this.oW.oW, null, this.xK(opcode), this.q(n2, this.oW.gP)));
               break;
            case 139:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.gO), this.Uv.createOperation(this.oW.gO, null, this.xK(opcode), this.q(n2, this.oW.gP)));
               break;
            case 140:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.nf), this.Uv.createOperation(this.oW.nf, null, this.xK(opcode), this.q(n2, this.oW.gP)));
               break;
            case 141:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.xK), this.Uv.createOperation(this.oW.xK, null, this.xK(opcode), this.q(n2, this.oW.zz)));
               break;
            case 142:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.Dw), this.Uv.createOperation(this.oW.Dw, null, this.xK(opcode), this.q(n2, this.oW.zz)));
               break;
            case 143:
               idInstruction = this.Dw
                  .q(DOpcodeType.IR_ASSIGN, this.q(j, this.oW.Uv), this.Uv.createOperation(this.oW.Uv, null, this.xK(opcode), this.q(n2, this.oW.zz)));
               break;
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 153:
            case 154:
               idInstruction = this.Dw
                  .q(
                     DOpcodeType.IR_ASSIGN,
                     this.q(j, this.Dw(opcode)),
                     this.Uv.createOperation(this.Dw(opcode), this.q(n2, this.Dw(opcode)), this.xK(opcode), this.q(n3, this.Dw(opcode)))
                  );
               break;
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
            case 160:
            case 161:
            case 162:
            case 166:
            case 167:
            case 168:
            case 169:
            case 170:
            case 171:
            case 172:
            case 173:
            case 174:
            case 175:
               idInstruction = this.Dw
                  .q(
                     DOpcodeType.IR_ASSIGN,
                     this.q(j, this.Dw(opcode)),
                     this.Uv.createOperation(this.Dw(opcode), this.q(n2, this.Dw(opcode)), this.xK(opcode), this.q(n3, this.Dw(opcode)))
                  );
               break;
            case 163:
            case 164:
            case 165:
               idInstruction = this.Dw
                  .q(
                     DOpcodeType.IR_ASSIGN,
                     this.q(j, this.Dw(opcode)),
                     this.Uv.createOperation(this.Dw(opcode), this.q(n2, this.Dw(opcode)), this.xK(opcode), this.q(n3, this.oW.zz))
                  );
               break;
            case 176:
            case 177:
            case 178:
            case 179:
            case 180:
            case 181:
            case 182:
            case 183:
            case 184:
            case 185:
            case 186:
               idInstruction = this.Dw
                  .q(
                     DOpcodeType.IR_ASSIGN,
                     this.q(j, this.oW.oW),
                     this.Uv.createOperation(this.oW.oW, this.q(j, this.Dw(opcode)), this.xK(opcode), this.q(n2, this.Dw(opcode)))
                  );
               break;
            case 187:
            case 188:
            case 189:
            case 190:
            case 191:
            case 192:
            case 193:
            case 194:
            case 198:
            case 199:
            case 200:
            case 201:
            case 202:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
               idInstruction = this.Dw
                  .q(
                     DOpcodeType.IR_ASSIGN,
                     this.q(j, this.Dw(opcode)),
                     this.Uv.createOperation(this.Dw(opcode), this.q(j, this.Dw(opcode)), this.xK(opcode), this.q(n2, this.Dw(opcode)))
                  );
               break;
            case 195:
            case 196:
            case 197:
               idInstruction = this.Dw
                  .q(
                     DOpcodeType.IR_ASSIGN,
                     this.q(j, this.Dw(opcode)),
                     this.Uv.createOperation(this.Dw(opcode), this.q(j, this.Dw(opcode)), this.xK(opcode), this.q(n2, this.oW.zz))
                  );
               break;
            case 208:
            case 210:
            case 211:
            case 212:
            case 213:
            case 214:
            case 215:
            case 216:
            case 218:
            case 219:
            case 220:
            case 221:
            case 222:
            case 223:
            case 224:
            case 225:
            case 226:
               idInstruction = this.Dw
                  .q(
                     DOpcodeType.IR_ASSIGN,
                     this.q(j, this.oW.zz),
                     this.Uv
                        .createOperation(
                           this.oW.zz,
                           this.q(n2, this.oW.zz),
                           this.xK(opcode),
                           this.Uv.createConstant((int)dalvikInstruction2.getParameterValue(2), this.oW.zz)
                        )
                  );
               break;
            case 209:
            case 217:
               idInstruction = this.Dw
                  .q(
                     DOpcodeType.IR_ASSIGN,
                     this.q(j, this.oW.zz),
                     this.Uv
                        .createOperation(
                           this.oW.zz,
                           this.Uv.createConstant((int)dalvikInstruction2.getParameterValue(2), this.oW.zz),
                           this.xK(opcode),
                           this.q(n2, this.oW.zz)
                        )
                  );
               break;
            case 254:
               int n21 = (int)dalvikInstruction2.getParameterValue(1);
               IDexMethodHandle methodHandle2 = this.nf.getMethodHandle(n21);
               String s = "";
               if (methodHandle2.getMethodHandleType().isFieldAccessor()) {
                  s = "_`" + this.nf.getField(methodHandle2.getFieldOrMethodIndex()).getName(false);
               } else if (methodHandle2.getMethodHandleType().isMethodInvoker()) {
                  s = "_`" + this.nf.getMethod(methodHandle2.getFieldOrMethodIndex()).getName(false);
               }

               IDexMethod addMethod = this.nf.addMethod("Ljeb/synthetic/PooledMethodHandles;", "Entry" + n21 + s, "()Ljava/lang/invoke/MethodHandle;");
               IJavaType q4 = this.oW.q("Ljava/lang/invoke/MethodHandle;");
               idInstruction = this.Dw
                  .q(
                     DOpcodeType.IR_ASSIGN,
                     this.q(j, q4),
                     this.Uv.createCallInfo(this.Uv.createIndex(addMethod.getIndex()), new IDExpression[0], q4, addMethod.getSignature(), DInvokeType.STATIC)
                  );
               break;
            case 255:
               IDexMethod addMethod2 = this.nf
                  .addMethod("Ljeb/synthetic/PooledMethodTypes;", "Entry" + (int)dalvikInstruction2.getParameterValue(1), "()Ljava/lang/invoke/MethodType;");
               IJavaType q5 = this.oW.q("Ljava/lang/invoke/MethodType;");
               idInstruction = this.Dw
                  .q(
                     DOpcodeType.IR_ASSIGN,
                     this.q(j, q5),
                     this.Uv.createCallInfo(this.Uv.createIndex(addMethod2.getIndex()), new IDExpression[0], q5, addMethod2.getSignature(), DInvokeType.STATIC)
                  );
               break;
            default:
               buv.q
                  .warn(
                     "%04Xh: Cannot convert Dalvik instruction \"%s\" (opcode: %Xh)", dalvikInstruction2.getOffset(), dalvikInstruction2.getMnemonic(), opcode
                  );
               throw new DexDecConversionException(dalvikInstruction2);
         }

         int m = (int)dalvikInstruction2.getOffset();
         this.xW.put(m, this.Me);
         this.KT.put(this.Me, m);
         this.HF.add(dalvikInstruction2.getOpcode());
         if (dalvikInstruction != null) {
            this.xW.put((int)dalvikInstruction.getOffset(), this.Me);
            this.HF.add(dalvikInstruction.getOpcode());
            this.oQ.add(dalvikInstruction.getOffset());
            dalvikInstruction = null;
         }

         int n44 = (int)dalvikInstruction2.getOffset();
         if (idInstruction != null) {
            idInstruction.setOffset(this.Me++);
            idInstruction.updateAllPhysicalOffsets(n44);
            list.add(idInstruction);
         }

         i++;
         if (list2 != null) {
            for (IDInstruction idInstruction3 : list2) {
               this.KT.put(this.Me, m);
               idInstruction3.setOffset(this.Me++);
               idInstruction3.updateAllPhysicalOffsets(n44);
               list.add(idInstruction3);
            }

            list2 = null;
         }
      }

      int n45 = (int)value.getEndAddress();
      this.xW.put(n45, this.Me);
      this.KT.put(this.Me, n45);
      return list;
   }

   private IDVar q(int index, int n, IJavaType javaType, IDalvikInstruction[] array, int[] array2) {
      array[0] = null;
      array2[0] = 0;
      BasicBlock basicBlock = this.lm.get(index);
      IDalvikInstruction dalvikInstruction = null;
      int opcode = 0;
      boolean b = false;

      while (array2[0] <= 10) {
         if (dalvikInstruction == null || (opcode = dalvikInstruction.getOpcode()) == 0) {
            if (++n >= basicBlock.size()) {
               n = 0;
               if (++index >= this.lm.size()) {
                  return null;
               }

               basicBlock = this.lm.get(index);
            }

            dalvikInstruction = (IDalvikInstruction)basicBlock.get(n);
         } else {
            if (opcode != 10 && opcode != 12 && opcode != 11) {
               if (opcode != 40 && opcode != 41 && opcode != 42) {
                  return null;
               }

               basicBlock = (BasicBlock<IDalvikInstruction>)this.JY
                  .get((long)((int)dalvikInstruction.getOffset() + 2 * (int)dalvikInstruction.getParameterValue(0)));
               if (basicBlock != null && !basicBlock.isEmpty()) {
                  index = this.lm.getBlocks().indexOf(basicBlock);
                  n = 0;
                  dalvikInstruction = (IDalvikInstruction)basicBlock.get(0);
                  int n2 = 0;
                  array2[0]++;
                  continue;
               }

               return null;
            }

            b = true;
            break;
         }
      }

      if (!b) {
         return null;
      } else {
         int n3;
         if (javaType == this.oW.RF
            || javaType == this.oW.xK
            || javaType == this.oW.Dw
            || javaType == this.oW.Uv
            || javaType == this.oW.oW
            || javaType == this.oW.nf) {
            n3 = 10;
         } else if (javaType == this.oW.gO || javaType == this.oW.gP) {
            n3 = 11;
         } else if (javaType.isObject()) {
            n3 = 12;
         } else {
            if (javaType != this.oW.zz) {
               if (javaType == this.oW.q) {
                  buv.q.warn(S.L("Potential illegal move-result for void-returning invocation"));
                  return null;
               }

               throw new RuntimeException("Unexpected type to store a return value: " + javaType);
            }

            n3 = 10;
         }

         if (n3 != opcode) {
            throw new RuntimeException("Unexpected move-result-xxx opcode");
         } else {
            IDVar q = this.q((int)dalvikInstruction.getParameterValue(0), javaType);
            array[0] = dalvikInstruction;
            return q;
         }
      }
   }

   private IDVar q(int n, int n2, IJavaType javaType, IDalvikInstruction[] array) {
      BasicBlock value = this.lm.get(n);
      IDalvikInstruction dalvikInstruction = null;
      if (n2 + 1 < value.size()) {
         dalvikInstruction = (IDalvikInstruction)value.get(n2 + 1);
      } else if (n + 1 < this.lm.size()) {
         dalvikInstruction = (IDalvikInstruction)this.lm.get(n + 1).get(0);
      }

      if (dalvikInstruction == null) {
         return null;
      } else {
         int opcode = dalvikInstruction.getOpcode();
         if (opcode == 10 || opcode == 11) {
            throw new RuntimeException();
         } else if (opcode != 12) {
            return null;
         } else {
            IDVar q = this.q((int)dalvikInstruction.getParameterValue(0), javaType);
            array[0] = dalvikInstruction;
            return q;
         }
      }
   }

   private IDalvikInstruction q(IDalvikInstruction dalvikInstruction, int n, Set set, Set set2, BasicBlock basicBlock, int n2) {
      int i = n;
      Long checkSingleDef = this.zz.checkSingleDef(dalvikInstruction.getOffset(), n);
      if (checkSingleDef == null) {
         return null;
      } else {
         IDalvikInstruction dalvikInstruction2 = (IDalvikInstruction)this.lm.getInstruction(checkSingleDef);
         if (dalvikInstruction2 == null) {
            return null;
         } else {
            int opcode = dalvikInstruction2.getOpcode();
            if (opcode != 34 && opcode != 1023) {
               do {
                  Collection blockDefUses = this.zz.getBlockDefUses(dalvikInstruction2.getOffset(), i);
                  if (blockDefUses == null) {
                     return null;
                  }

                  int opcode2 = dalvikInstruction2.getOpcode();
                  if (opcode2 != 34 && opcode2 != 1023 && opcode2 != 7 && opcode2 != 8 && opcode2 != 9) {
                     return null;
                  }

                  Assert.a(dalvikInstruction2.getParameterValue(0) == i);
                  Iterator iterator = blockDefUses.iterator();

                  while (iterator.hasNext()) {
                     int indexOfInstruction = basicBlock.getIndexOfInstruction((Long)iterator.next());
                     if (indexOfInstruction < n2 && indexOfInstruction >= 0) {
                        IDalvikInstruction dalvikInstruction3 = (IDalvikInstruction)basicBlock.get(indexOfInstruction);
                        int opcode3 = dalvikInstruction3.getOpcode();
                        if (opcode3 != 7 && opcode3 != 8 && opcode3 != 9) {
                           return null;
                        }

                        int j = (int)dalvikInstruction3.getParameterValue(0);
                        if (j != n) {
                           set.add(j);
                        }

                        set2.add(dalvikInstruction3);
                     }
                  }

                  if (opcode2 == 34 || opcode2 == 1023) {
                     return dalvikInstruction2;
                  }

                  i = (int)dalvikInstruction2.getParameterValue(1);
                  set.add(i);
                  set2.add(dalvikInstruction2);
                  Long checkSingleDef2 = this.zz.checkSingleDef(dalvikInstruction2.getOffset(), i);
                  if (checkSingleDef2 == null) {
                     return null;
                  }

                  dalvikInstruction2 = (IDalvikInstruction)this.lm.getInstruction(checkSingleDef2);
               } while (dalvikInstruction2 != null);

               return null;
            } else {
               Assert.a(dalvikInstruction2.getParameterValue(0) == n);
               return dalvikInstruction2;
            }
         }
      }
   }

   private IJavaOperator xK(int n) {
      switch (n) {
         case 32:
            return this.gO.PV;
         case 33:
         case 34:
         case 35:
         case 36:
         case 37:
         case 38:
         case 39:
         case 40:
         case 41:
         case 42:
         case 43:
         case 44:
         case 45:
         case 46:
         case 47:
         case 48:
         case 49:
         case 62:
         case 63:
         case 64:
         case 65:
         case 66:
         case 67:
         case 68:
         case 69:
         case 70:
         case 71:
         case 72:
         case 73:
         case 74:
         case 75:
         case 76:
         case 77:
         case 78:
         case 79:
         case 80:
         case 81:
         case 82:
         case 83:
         case 84:
         case 85:
         case 86:
         case 87:
         case 88:
         case 89:
         case 90:
         case 91:
         case 92:
         case 93:
         case 94:
         case 95:
         case 96:
         case 97:
         case 98:
         case 99:
         case 100:
         case 101:
         case 102:
         case 103:
         case 104:
         case 105:
         case 106:
         case 107:
         case 108:
         case 109:
         case 110:
         case 111:
         case 112:
         case 113:
         case 114:
         case 115:
         case 116:
         case 117:
         case 118:
         case 119:
         case 120:
         case 121:
         case 122:
         default:
            throw new RuntimeException();
         case 50:
         case 56:
            return this.gO.oQ;
         case 51:
         case 57:
            return this.gO.xW;
         case 52:
         case 58:
            return this.gO.KT;
         case 53:
         case 59:
            return this.gO.Gf;
         case 54:
         case 60:
            return this.gO.Ef;
         case 55:
         case 61:
            return this.gO.cC;
         case 123:
         case 125:
         case 127:
         case 128:
            return this.gO.HF;
         case 124:
         case 126:
            return this.gO.LK;
         case 129:
            return this.gO.Dz;
         case 130:
         case 131:
         case 133:
         case 134:
         case 135:
         case 136:
         case 138:
         case 139:
            return this.gO.EB;
         case 132:
            return this.gO.If;
         case 137:
            return this.gO.jq;
         case 140:
            return this.gO.mI;
         case 141:
            return this.gO.sH;
         case 142:
            return this.gO.CE;
         case 143:
            return this.gO.wF;
         case 144:
         case 155:
         case 166:
         case 171:
         case 176:
         case 187:
         case 198:
         case 203:
         case 208:
         case 216:
            return this.gO.xK;
         case 145:
         case 156:
         case 167:
         case 172:
         case 177:
         case 188:
         case 199:
         case 204:
         case 209:
         case 217:
            return this.gO.Dw;
         case 146:
         case 157:
         case 168:
         case 173:
         case 178:
         case 189:
         case 200:
         case 205:
         case 210:
         case 218:
            return this.gO.Uv;
         case 147:
         case 158:
         case 169:
         case 174:
         case 179:
         case 190:
         case 201:
         case 206:
         case 211:
         case 219:
            return this.gO.oW;
         case 148:
         case 159:
         case 170:
         case 175:
         case 180:
         case 191:
         case 202:
         case 207:
         case 212:
         case 220:
            return this.gO.gO;
         case 149:
         case 160:
         case 181:
         case 192:
         case 213:
         case 221:
            return this.gO.nf;
         case 150:
         case 161:
         case 182:
         case 193:
         case 214:
         case 222:
            return this.gO.gP;
         case 151:
         case 162:
         case 183:
         case 194:
         case 215:
         case 223:
            return this.gO.za;
         case 152:
         case 163:
         case 184:
         case 195:
         case 224:
            return this.gO.lm;
         case 153:
         case 164:
         case 185:
         case 196:
         case 225:
            return this.gO.zz;
         case 154:
         case 165:
         case 186:
         case 197:
         case 226:
            return this.gO.JY;
      }
   }

   private IJavaType Dw(int n) {
      switch (n) {
         case 45:
         case 46:
            return this.oW.nf;
         case 47:
         case 48:
            return this.oW.gP;
         case 49:
            return this.oW.gO;
         case 68:
         case 75:
         case 82:
         case 89:
         case 96:
         case 103:
         case 1791:
         case 3583:
         case 5375:
         case 7167:
            return this.oW.za;
         case 69:
         case 76:
         case 83:
         case 90:
         case 97:
         case 104:
         case 2047:
         case 3839:
         case 5631:
         case 7423:
            return this.oW.lm;
         case 70:
         case 77:
         case 84:
         case 91:
         case 98:
         case 105:
         case 2303:
         case 4095:
         case 5887:
         case 7679:
            return this.oW.JY;
         case 71:
         case 78:
         case 85:
         case 92:
         case 99:
         case 106:
         case 2559:
         case 4351:
         case 6143:
         case 7935:
            return this.oW.RF;
         case 72:
         case 79:
         case 86:
         case 93:
         case 100:
         case 107:
         case 2815:
         case 4607:
         case 6399:
         case 8191:
            return this.oW.xK;
         case 73:
         case 80:
         case 87:
         case 94:
         case 101:
         case 108:
         case 3071:
         case 4863:
         case 6655:
         case 8447:
            return this.oW.Dw;
         case 74:
         case 81:
         case 88:
         case 95:
         case 102:
         case 109:
         case 3327:
         case 5119:
         case 6911:
         case 8703:
            return this.oW.Uv;
         case 123:
         case 124:
            return this.oW.zz;
         case 125:
         case 126:
            return this.oW.gO;
         case 127:
            return this.oW.nf;
         case 128:
            return this.oW.gP;
         case 144:
         case 145:
         case 146:
         case 147:
         case 148:
         case 149:
         case 150:
         case 151:
         case 152:
         case 153:
         case 154:
            return this.oW.zz;
         case 155:
         case 156:
         case 157:
         case 158:
         case 159:
         case 160:
         case 161:
         case 162:
         case 163:
         case 164:
         case 165:
            return this.oW.gO;
         case 166:
         case 167:
         case 168:
         case 169:
         case 170:
            return this.oW.nf;
         case 171:
         case 172:
         case 173:
         case 174:
         case 175:
            return this.oW.gP;
         case 176:
         case 177:
         case 178:
         case 179:
         case 180:
         case 181:
         case 182:
         case 183:
         case 184:
         case 185:
         case 186:
            return this.oW.zz;
         case 187:
         case 188:
         case 189:
         case 190:
         case 191:
         case 192:
         case 193:
         case 194:
         case 195:
         case 196:
         case 197:
            return this.oW.gO;
         case 198:
         case 199:
         case 200:
         case 201:
         case 202:
            return this.oW.nf;
         case 203:
         case 204:
         case 205:
         case 206:
         case 207:
            return this.oW.gP;
         default:
            throw new RuntimeException();
      }
   }

   public static boolean q(int i) {
      return EB.contains(i);
   }

   public static Set oW() {
      return Collections.unmodifiableSet(EB);
   }

   static {
      (EB = new HashSet()).add(116);
      EB.add(117);
      EB.add(118);
      EB.add(119);
      EB.add(120);
      EB.add(251);
      EB.add(123);
      EB.add(124);
      EB.add(127);
      EB.add(125);
      EB.add(126);
      EB.add(128);
      EB.add(129);
      EB.add(130);
      EB.add(131);
      EB.add(132);
      EB.add(133);
      EB.add(134);
      EB.add(135);
      EB.add(136);
      EB.add(137);
      EB.add(138);
      EB.add(139);
      EB.add(140);
      EB.add(141);
      EB.add(142);
      EB.add(143);
      EB.add(144);
      EB.add(145);
      EB.add(146);
      EB.add(147);
      EB.add(148);
      EB.add(149);
      EB.add(150);
      EB.add(151);
      EB.add(152);
      EB.add(153);
      EB.add(154);
      EB.add(166);
      EB.add(167);
      EB.add(168);
      EB.add(169);
      EB.add(170);
      EB.add(155);
      EB.add(156);
      EB.add(157);
      EB.add(158);
      EB.add(159);
      EB.add(160);
      EB.add(161);
      EB.add(162);
      EB.add(171);
      EB.add(172);
      EB.add(173);
      EB.add(174);
      EB.add(175);
      EB.add(163);
      EB.add(164);
      EB.add(165);
      EB.add(176);
      EB.add(177);
      EB.add(178);
      EB.add(179);
      EB.add(180);
      EB.add(181);
      EB.add(182);
      EB.add(183);
      EB.add(184);
      EB.add(185);
      EB.add(186);
      EB.add(198);
      EB.add(199);
      EB.add(200);
      EB.add(201);
      EB.add(202);
      EB.add(187);
      EB.add(188);
      EB.add(189);
      EB.add(190);
      EB.add(191);
      EB.add(192);
      EB.add(193);
      EB.add(194);
      EB.add(203);
      EB.add(204);
      EB.add(205);
      EB.add(206);
      EB.add(207);
      EB.add(195);
      EB.add(196);
      EB.add(197);
      EB.add(208);
      EB.add(210);
      EB.add(211);
      EB.add(212);
      EB.add(213);
      EB.add(214);
      EB.add(215);
      EB.add(216);
      EB.add(218);
      EB.add(219);
      EB.add(220);
      EB.add(221);
      EB.add(222);
      EB.add(223);
      EB.add(224);
      EB.add(225);
      EB.add(226);
      EB.add(209);
      EB.add(217);
   }

   private class CU {
      static int q = 30;
      private IJavaType xK;
      private Set Dw = new HashSet();
      private int Uv;

      IJavaType q(IDalvikInstruction dalvikInstruction) {
         return this.q(dalvikInstruction, (int)dalvikInstruction.getParameterValue(0)) <= 0 ? null : this.xK;
      }

      private int q(IDalvikInstruction dalvikInstruction, int n) {
         if (!this.Dw.add(dalvikInstruction.getOffset())) {
            return 0;
         } else {
            for (long longValue : buv.this.zz.getUseDefs(dalvikInstruction.getOffset(), n)) {
               if (longValue >= 0L) {
                  IDalvikInstruction dalvikInstruction2 = (IDalvikInstruction)buv.this.lm.getInstruction(longValue);
                  if (dalvikInstruction2 != null) {
                     int opcode = dalvikInstruction2.getOpcode();
                     if (opcode != 7 && opcode != 9 && opcode != 8) {
                        if (opcode == 35 || opcode == 1279) {
                           if ((int)dalvikInstruction2.getParameterValue(0) != n) {
                              return -2;
                           } else {
                              IJavaType type = buv.this.oW.parseType(buv.this.nf.getType((int)dalvikInstruction2.getParameterValue(2)).getSignature(false));
                              if (type.getDimensions() <= 0) {
                                 throw new RuntimeException("Expected an array type for new-array");
                              } else {
                                 this.xK = type;
                                 return 1;
                              }
                           }
                        }

                        if (opcode == 12) {
                           Couple instructionLocation = buv.this.lm.getInstructionLocation(dalvikInstruction2.getOffset());
                           if (instructionLocation != null) {
                              BasicBlock basicBlock = (BasicBlock)instructionLocation.getFirst();
                              int intValue = (Integer)instructionLocation.getSecond();
                              if (intValue >= 1) {
                                 IDalvikInstruction dalvikInstruction3 = (IDalvikInstruction)basicBlock.get(intValue - 1);
                                 switch (dalvikInstruction3.getOpcode()) {
                                    case 110:
                                    case 111:
                                    case 112:
                                    case 113:
                                    case 114:
                                    case 116:
                                    case 117:
                                    case 118:
                                    case 119:
                                    case 120:
                                       IDexType returnType = buv.this.nf.getMethod((int)dalvikInstruction3.getParameterValue(0)).getReturnType();
                                       if (returnType.isArray()) {
                                          IJavaType type2 = buv.this.oW.parseType(returnType.getSignature(false));
                                          if (type2.getDimensions() <= 0) {
                                             throw new RuntimeException("Expected an array type returned by invoke-xxx");
                                          }

                                          this.xK = type2;
                                          return 1;
                                       }
                                    case 115:
                                 }
                              }
                           }
                        }
                     } else {
                        if ((int)dalvikInstruction2.getParameterValue(0) != n) {
                           return -2;
                        }

                        int q = this.q(dalvikInstruction2, (int)dalvikInstruction2.getParameterValue(1));
                        if (q != 0) {
                           return q;
                        }
                     }
                  }
               }
            }

            return 0;
         }
      }
   }

   private class eo {
      List q = new ArrayList();

      public eo() {
      }

      public IDTarget q(int n) {
         IDTarget target = buv.this.Uv.createTarget(n);
         this.q.add(target);
         return target;
      }

      public List q() {
         return this.q;
      }
   }
}
