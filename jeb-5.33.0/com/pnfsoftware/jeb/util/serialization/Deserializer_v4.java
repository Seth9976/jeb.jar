package com.pnfsoftware.jeb.util.serialization;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.reflect.ReflectionHelper;
import com.pnfsoftware.jeb.util.serialization.annotations.SerConstructor;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.AbstractList;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

class Deserializer_v4 extends AbstractInternalDeserializer {
   private static final ILogger logger = GlobalLog.getLogger(Deserializer_v4.class);
   private List stringpool;

   public Deserializer_v4(ITypeIdProvider var1, List var2, LEDataInputStream var3) {
      super(var1, var2, var3);
   }

   public void setStringPool(List var1) {
      this.stringpool = var1;
   }

   public List getStringPool() {
      return this.stringpool;
   }

   @Override
   protected Object readInternal(Object var1, Class var2) throws IOException {
      boolean var3 = var2 == null;
      if (var3 && var1 != null) {
         throw new SerializationException("Object should not be provided, a leaf is being deserialized", var2);
      } else {
         Object var4 = var1;
         int var5 = this.in.readIntULEB128();
         int var6 = 0;
         String var7 = null;
         int var9 = 0;
         int var8;
         if (var3) {
            if ((var5 & 16) != 0) {
               var9 = this.in.readByte() & 255;
            }

            if (var5 == 1) {
               return null;
            }

            if ((var5 & 64) != 0) {
               int var29 = var5 & 63;
               switch (var29) {
                  case 0:
                     return Boolean.FALSE;
                  case 1:
                     return Boolean.TRUE;
                  case 2:
                     return this.in.readIntULEB128();
                  case 3:
                     return this.in.readLongULEB128();
                  case 4:
                     return "";
                  case 5:
                     return 0;
                  case 6:
                     return 1;
                  case 7:
                     return -1;
                  case 8:
                     return 0L;
                  case 9:
                     return 1L;
                  case 10:
                     return -1L;
                  default:
                     throw new SerializationException("Unknown special object index: " + var29);
               }
            }

            var6 = this.in.readIntULEB128();
            if ((var5 & 4) != 0) {
               Object var28 = this.objmap.get(var6);
               if (var28 == null) {
                  throw new SerializationException(Strings.ff("Expected an already-deserialized object for id: %d", var6), var2);
               }

               return var28;
            }

            var8 = this.in.readIntULEB128() - 128;
            if ((var5 & 256) != 0) {
               int var10 = this.in.readIntULEB128();
               byte[] var11 = new byte[var10];
               this.in.readFully(var11);
               var7 = Strings.decodeUTF8(var11);
            } else if ((var5 & 512) != 0) {
               int var26 = this.in.readIntULEB128();
               Class var33 = this.objmap.get(var26).getClass();
               var7 = ReflectionHelper.reduceDimensions(var33).getName();
            }

            Class var27;
            try {
               if (var8 > 0) {
                  var27 = this.customTypeIdProvider.getType(var8);
               } else if (var8 < 0) {
                  var27 = this.nativeTypeIdProvider.getType(var8);
               } else {
                  var27 = this.loadClass(var7);
               }
            } catch (ClassNotFoundException var23) {
               throw new SerializationException(Strings.ff("Class not found for type id: %d", var8), var23, var2);
            }

            if (var2 != null && var2 != var27) {
               throw new SerializationException(Strings.ff("Unexpected class was found for type id %d: %s", var8, var27), var2);
            }

            var2 = var27;
            if (var27 == null) {
               throw new SerializationException(Strings.ff("Type was expected for id %d, got null", var8), var27);
            }
         } else {
            var8 = this.nativeTypeIdProvider.getId(var2);
            if (var8 == 0) {
               var8 = this.customTypeIdProvider.getId(var2);
            }
         }

         if ((var5 & 2) != 0) {
            int var32 = this.in.readIntULEB128();
            var4 = var2.getEnumConstants()[var32];
            this.registerObject(true, var6, var4);
            return var4;
         } else if ((var5 & 128) != 0) {
            int var31 = this.in.readIntULEB128();

            try {
               var4 = var2.getDeclaredMethod("valueOf", int.class).invoke(null, var31);
            } catch (ReflectiveOperationException var17) {
               throw new SerializationException(var17);
            }

            this.registerObject(true, var6, var4);
            return var4;
         } else {
            int var30 = (var5 & 32) != 0 ? this.in.readIntULEB128() : 0;
            if (var8 >= 0 && var9 == 0) {
               if (var3) {
                  Object var51 = this.constructorMap.get(var2);
                  if (var51 == null) {
                     var51 = this.mootObject;

                     try {
                        Constructor var67 = var2.getDeclaredConstructor();
                        if (var67.getAnnotation(SerConstructor.class) != null) {
                           var51 = var67;
                        }
                     } catch (NoSuchMethodException var21) {
                     } catch (Exception var22) {
                        throw new SerializationException(var22, var2);
                     }

                     this.constructorMap.put(var2, var51);
                  }

                  try {
                     if (var51 instanceof Constructor) {
                        var4 = ReflectionHelper.newInstance2((Constructor)var51);
                     } else {
                        var4 = this.objenesis.getInstantiatorOf(var2).newInstance();
                     }
                  } catch (Exception var20) {
                     throw new SerializationException(var20, var2);
                  }

                  this.registerObject(var3, var6, var4);
               }

               Class var52 = var2.getSuperclass();
               if (var52 != null) {
                  this.read(var4, var52);
               }

               AbstractInternalDeserializer.CustomMethods var68 = this.getCustomMethods(var4, var2);
               if (var68.mCustomRead != null) {
                  try {
                     ReflectionHelper.invoke2(var68.mCustomRead, var4, new DeserializerHelper(this, var4, var2, var30));
                  } catch (Exception var19) {
                     throw new SerializationException("Custom read failed", var19, var2);
                  }
               } else {
                  this.restoreFields(var4, var2);
               }

               if (var68.mCustomInit != null) {
                  try {
                     ReflectionHelper.invoke2(var68.mCustomInit, var4);
                  } catch (Exception var18) {
                     throw new SerializationException("Initializer failed", var18, var2);
                  }
               }

               this.notifyDeserializationEvent(1, var2, var4);
            } else if (var9 > 0) {
               int[] var34 = new int[var9];
               int var12 = this.in.readIntULEB128();
               var34[0] = var12;
               Object var13 = Array.newInstance(var2, var34);
               this.registerObject(var3, var6, var13);

               for (int var14 = 0; var14 < var12; var14++) {
                  Array.set(var13, var14, this.read());
               }

               var4 = var13;
            } else if (var2 == Object.class) {
               if (var1 == null) {
                  var1 = new Object();
               }

               var4 = var1;
            } else if (var2 == Class.class) {
               String var35 = null;
               int var53 = this.in.readInt();
               if (var53 == 0) {
                  var35 = this.in.readUTF();
               }

               var4 = this.retrieveClassFromIdOrName(var53, var35);
            } else if (var2 == Boolean.class) {
               var4 = this.in.readByte() == 0 ? Boolean.FALSE : Boolean.TRUE;
            } else if (var2 == boolean[].class) {
               int var36 = this.in.readIntULEB128();
               boolean[] var54 = new boolean[var36];

               for (int var69 = 0; var69 < var36; var69++) {
                  var54[var69] = this.in.readByte() != 0;
               }

               var4 = var54;
            } else if (var2 == Byte.class) {
               var4 = this.in.readByte();
            } else if (var2 == byte[].class) {
               int var37 = this.in.readIntULEB128();
               byte[] var55 = new byte[var37];

               for (int var70 = 0; var70 < var37; var70++) {
                  var55[var70] = this.in.readByte();
               }

               var4 = var55;
            } else if (var2 == Character.class) {
               var4 = this.in.readChar();
            } else if (var2 == char[].class) {
               int var38 = this.in.readIntULEB128();
               char[] var56 = new char[var38];

               for (int var71 = 0; var71 < var38; var71++) {
                  var56[var71] = this.in.readChar();
               }

               var4 = var56;
            } else if (var2 == Short.class) {
               var4 = this.in.readShort();
            } else if (var2 == short[].class) {
               int var39 = this.in.readIntULEB128();
               short[] var57 = new short[var39];

               for (int var72 = 0; var72 < var39; var72++) {
                  var57[var72] = this.in.readShort();
               }

               var4 = var57;
            } else if (var2 == Integer.class) {
               var4 = this.in.readIntULEB128();
            } else if (var2 == int[].class) {
               int var40 = this.in.readIntULEB128();
               int[] var58 = new int[var40];

               for (int var73 = 0; var73 < var40; var73++) {
                  var58[var73] = this.in.readIntULEB128();
               }

               var4 = var58;
            } else if (var2 == Long.class) {
               var4 = this.in.readLongULEB128();
            } else if (var2 == long[].class) {
               int var41 = this.in.readIntULEB128();
               long[] var59 = new long[var41];

               for (int var74 = 0; var74 < var41; var74++) {
                  var59[var74] = this.in.readLongULEB128();
               }

               var4 = var59;
            } else if (var2 == Float.class) {
               var4 = this.in.readFloat();
            } else if (var2 == float[].class) {
               int var42 = this.in.readIntULEB128();
               float[] var60 = new float[var42];

               for (int var75 = 0; var75 < var42; var75++) {
                  var60[var75] = this.in.readFloat();
               }

               var4 = var60;
            } else if (var2 == Double.class) {
               var4 = this.in.readDouble();
            } else if (var2 == double[].class) {
               int var43 = this.in.readIntULEB128();
               double[] var61 = new double[var43];

               for (int var76 = 0; var76 < var43; var76++) {
                  var61[var76] = this.in.readDouble();
               }

               var4 = var61;
            } else if (var2 == String.class) {
               if (this.stringpool != null) {
                  int var44 = this.in.readIntULEB128();
                  var4 = this.stringpool.get(var44);
               } else {
                  int var45 = this.in.readIntULEB128();
                  byte[] var62 = new byte[var45];
                  this.in.readFully(var62);
                  var4 = Strings.decodeUTF8(var62);
               }
            } else if (var2 == BigInteger.class) {
               int var46 = this.in.readIntULEB128();
               byte[] var63 = new byte[var46];
               this.in.readFully(var63);
               var4 = new BigInteger(var63);
            } else if (var2 == BigDecimal.class) {
               int var47 = this.in.readIntULEB128();
               byte[] var64 = new byte[var47];
               this.in.readFully(var64);
               var4 = new BigDecimal(Strings.decodeUTF8(var64));
            } else if (var2 == AtomicBoolean.class) {
               var4 = new AtomicBoolean(this.in.readByte() != 0);
            } else if (var2 == AtomicInteger.class) {
               var4 = new AtomicInteger(this.in.readIntULEB128());
            } else if (var2 == AtomicLong.class) {
               var4 = new AtomicLong(this.in.readLongULEB128());
            } else if (var2 == SoftReference.class) {
               if (!var3) {
                  throw new RuntimeException("Unexpected non-lead SoftReference object");
               }

               var4 = new SoftReference(this.read());
            } else if (var2 == WeakReference.class) {
               if (!var3) {
                  throw new RuntimeException("Unexpected non-lead WeakReference object");
               }

               var4 = new WeakReference(this.read());
            } else if (Collection.class.isAssignableFrom(var2)) {
               if (var2 == ArrayList.class
                  || var2 == LinkedList.class
                  || var2 == ArrayDeque.class
                  || var2 == HashSet.class
                  || var2 == TreeSet.class
                  || var2 == LinkedHashSet.class
                  || var2 == CopyOnWriteArrayList.class
                  || var2 == CopyOnWriteArraySet.class
                  || var2 == ConcurrentSkipListSet.class
                  || var2 == ConcurrentLinkedQueue.class) {
                  int var49 = this.in.readIntULEB128();
                  Object var65;
                  if (var3) {
                     if (var2 == ArrayList.class) {
                        var65 = new ArrayList(var49);
                     } else if (var2 == LinkedList.class) {
                        var65 = new LinkedList();
                     } else if (var2 == ArrayDeque.class) {
                        var65 = new ArrayDeque(var49);
                     } else if (var2 == HashSet.class) {
                        var65 = new HashSet(var49);
                     } else if (var2 == TreeSet.class) {
                        var65 = new TreeSet();
                     } else if (var2 == LinkedHashSet.class) {
                        var65 = new LinkedHashSet();
                     } else if (var2 == CopyOnWriteArrayList.class) {
                        var65 = new CopyOnWriteArrayList();
                     } else if (var2 == CopyOnWriteArraySet.class) {
                        var65 = new CopyOnWriteArraySet();
                     } else if (var2 == ConcurrentSkipListSet.class) {
                        var65 = new ConcurrentSkipListSet();
                     } else {
                        if (var2 != ConcurrentLinkedQueue.class) {
                           throw new SerializationException("Unsupported collection type", var2);
                        }

                        var65 = new ConcurrentLinkedQueue();
                     }
                  } else {
                     var65 = (Collection)var1;
                  }

                  this.registerObject(var3, var6, var65);
                  PreCollection var77 = new PreCollection((Collection)var65, var49);

                  for (int var79 = 0; var79 < var49; var79++) {
                     Object var15 = this.read();
                     var77.recordObj(var15);
                  }

                  this.deferredObjects.add(var77);
                  var4 = var65;
               } else if (var2 == AbstractList.class) {
                  if (var3) {
                     throw new RuntimeException();
                  }

                  Collection var48 = (Collection)var1;
                  var4 = var48;
               }
            } else {
               if (!Map.class.isAssignableFrom(var2)
                  || var2 != HashMap.class
                     && var2 != TreeMap.class
                     && var2 != LinkedHashMap.class
                     && var2 != IdentityHashMap.class
                     && var2 != ConcurrentHashMap.class
                     && var2 != ConcurrentSkipListMap.class) {
                  throw new SerializationException("Unsupported type", var2);
               }

               int var50 = this.in.readIntULEB128();
               Object var66;
               if (var3) {
                  if (var2 == HashMap.class) {
                     var66 = new HashMap(var50);
                  } else if (var2 == TreeMap.class) {
                     var66 = new TreeMap();
                  } else if (var2 == LinkedHashMap.class) {
                     var66 = new LinkedHashMap(var50);
                  } else if (var2 == IdentityHashMap.class) {
                     var66 = new IdentityHashMap(var50);
                  } else if (var2 == ConcurrentHashMap.class) {
                     var66 = new ConcurrentHashMap(var50);
                  } else {
                     if (var2 != ConcurrentSkipListMap.class) {
                        throw new SerializationException("Unsupported map type", var2);
                     }

                     var66 = new ConcurrentSkipListMap();
                  }
               } else {
                  var66 = (Map)var1;
               }

               this.registerObject(var3, var6, var66);
               PreMap var78 = new PreMap((Map)var66, var50);

               for (int var80 = 0; var80 < var50; var80++) {
                  Object var81 = this.read();
                  var78.recordObj(var81);
                  Object var16 = this.read();
                  var78.recordObj(var16);
               }

               this.deferredObjects.add(var78);
               var4 = var66;
            }

            this.registerObject(var3, var6, var4);
            return var4;
         }
      }
   }

   @Override
   public void restoreFields(Object var1, Class var2) throws IOException {
      while (true) {
         int var3 = this.in.readIntULEB128();
         if (var3 == 0) {
            return;
         }

         Object var4 = this.read();
         this.restoreFieldValue(var1, var2, var3, var4);
      }
   }

   private Class retrieveClassFromIdOrName(int var1, String var2) throws SerializationException {
      try {
         Class var3;
         if (var1 > 0) {
            var3 = this.customTypeIdProvider.getType(var1);
         } else if (var1 < 0) {
            var3 = this.nativeTypeIdProvider.getType(var1);
         } else {
            var3 = this.loadClass(var2);
         }

         return var3;
      } catch (ClassNotFoundException var5) {
         throw new SerializationException(Strings.ff("Class not found for type id: %d / %s", var1, var2), var5);
      }
   }
}
