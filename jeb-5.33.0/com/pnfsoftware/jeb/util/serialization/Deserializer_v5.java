package com.pnfsoftware.jeb.util.serialization;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.IProgressCallback;
import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.reflect.ReflectionHelper;
import com.pnfsoftware.jeb.util.serialization.annotations.SerConstructor;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInit;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomRead;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
import java.util.Set;
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
import org.objenesis.ObjenesisStd;

class Deserializer_v5 implements IInternalDeserializer {
   private static final ILogger logger = GlobalLog.getLogger(Deserializer_v5.class);
   protected LEDataInputStream in;
   protected ITypeIdProvider nativeTypeIdProvider;
   protected ITypeIdProvider customTypeIdProvider;
   protected List classloaders;
   protected ObjenesisStd objenesis;
   protected Map constructorMap = new HashMap();
   protected Object mootObject = new Object();
   protected ArrayList objlist = new ArrayList();
   protected int objcnt = 0;
   private List deferredObjects = new ArrayList();
   private Map objmap = new Deserializer_v5.ObjlistAsMap();
   private static Map fieldCache = new HashMap();
   List deferredFieldValues = new ArrayList();
   protected static Map customMethods = new HashMap();
   protected int expectedObjectCount;
   protected List progressCallbacks = new ArrayList();
   protected MultiMap objectCreatedHookMap = new MultiMap();
   private List stringpool;
   private Set requestedObjectIds = new LinkedHashSet();

   protected Deserializer_v5(ITypeIdProvider var1, List var2, LEDataInputStream var3) {
      this.in = var3;
      this.nativeTypeIdProvider = NativeTypeIdProvider.getInstance();
      this.customTypeIdProvider = var1;
      this.classloaders = var2;
      this.objenesis = new ObjenesisStd();
      this.objlist.add(null);
   }

   @Override
   public int getObjectCount() {
      return this.objcnt;
   }

   @Override
   public Collection getObjects() {
      return (Collection)(this.objlist.isEmpty() ? this.objlist : this.objlist.subList(1, this.objlist.size()));
   }

   private Object objPut(int var1, Object var2) {
      Assert.a(var1 >= 1);
      if (var1 < this.objlist.size()) {
         Object var4 = this.objlist.set(var1, var2);
         if (var4 == null) {
            this.objcnt++;
         }

         return var4;
      } else {
         this.objlist.ensureCapacity(var1 + 1);

         for (int var3 = this.objlist.size(); var3 < var1; var3++) {
            this.objlist.add(null);
         }

         this.objlist.add(var2);
         this.objcnt++;
         return null;
      }
   }

   private Object objGet(int var1) {
      Assert.a(var1 >= 1);
      return var1 >= this.objlist.size() ? null : this.objlist.get(var1);
   }

   @Override
   public InputStream getStream() {
      return new Deserializer_v5$1(this);
   }

   @Override
   public Object deserializeInternal() throws IOException {
      Assert.a(this.requestedObjectIds.isEmpty());
      Object var1 = this.read();

      while (!this.requestedObjectIds.isEmpty()) {
         this.read();
      }

      for (Deserializer_v5.DeferredFieldValue var3 : this.deferredFieldValues) {
         this.verifyNonInterrupted();
         Object var4 = this.objGet(var3.fieldObjectId);
         if (var4 == null) {
            throw new RuntimeException(Strings.ff("Unexpected null object (id=%d): %s", var3.fieldObjectId, var3.f));
         }

         if (var4 instanceof Deserializer_v5.TemporaryReference) {
            Object var5 = ((Deserializer_v5.TemporaryReference)var4).build(this.objmap);
            if (var5 != var4) {
               this.objPut(var3.fieldObjectId, var5);
               var4 = var5;
            }
         }

         var3.f.setAccessible(true);

         try {
            var3.f.set(var3.o, var4);
         } catch (IllegalAccessException var10) {
            throw new SerializationException(var10);
         }
      }

      this.resolveDeferredObjects();
      HashSet var11 = new HashSet();
      int var12 = 1;

      for (Object var14 : this.getObjects()) {
         this.verifyNonInterrupted();
         if (var14 != null && var11.add(var12)) {
            Class var6 = var14.getClass();

            do {
               Deserializer_v5.CustomMethods var7 = this.getCustomMethods(var14, var6);
               if (var7.mCustomInitPostGraph != null) {
                  try {
                     ReflectionHelper.invoke2(var7.mCustomInitPostGraph, var14);
                  } catch (Exception var9) {
                     throw new SerializationException("Post-graph initializer failed", var9, null);
                  }
               }

               var6 = var6.getSuperclass();
            } while (var6 != null);
         }

         var12++;
      }

      this.notifyProgressCallbacks();
      return var1;
   }

   public void close() throws IOException {
      this.in.close();
      this.in = null;
   }

   @Override
   public Object read() throws IOException {
      return this.read(null, null, null);
   }

   private void verifyNonInterrupted() throws SerializationCancelledException {
      if (Thread.interrupted()) {
         throw new SerializationCancelledException("The deserialization process was cancelled");
      }
   }

   public Object read(Object var1, Class var2, int[] var3) throws IOException {
      this.verifyNonInterrupted();
      return this.readInternal(var1, var2, var3);
   }

   protected void registerObject(boolean var1, int var2, Object var3) {
      if (var1) {
         Object var4 = this.objPut(var2, var3);
         if (var4 != var3) {
            int var5 = this.objcnt;
            if (var5 % 200000 == 0) {
               logger.debug("Deserializing... status: %d unique objects", var5);
               this.notifyProgressCallbacks();
            }
         }
      }
   }

   protected Class loadClass(String var1) throws ClassNotFoundException {
      for (ClassLoader var3 : this.classloaders) {
         try {
            return var3.loadClass(var1);
         } catch (ClassNotFoundException var4) {
         }
      }

      return ClassLoader.getSystemClassLoader().loadClass(var1);
   }

   protected void restoreFieldValue(Object var1, Class var2, int var3, Object var4, int var5) throws SerializationException {
      Class var6 = var2 == null ? var1.getClass() : var2;
      Object var7 = (Map)fieldCache.get(var6);
      if (var7 == null) {
         var7 = new HashMap();
         fieldCache.put(var6, var7);

         for (Field var11 : var6.getDeclaredFields()) {
            SerId var12 = (SerId)var11.getAnnotation(SerId.class);
            if (var12 != null) {
               var7.put(var12.value(), var11);
            }
         }
      }

      Field var14 = (Field)var7.get(var3);
      if (var14 != null) {
         if (var4 == null && var5 > 0) {
            this.deferredFieldValues.add(new Deserializer_v5.DeferredFieldValue(var14, var1, var5));
         } else if (var4 instanceof Deserializer_v5.TemporaryReference) {
            this.deferredFieldValues.add(new Deserializer_v5.DeferredFieldValue(var14, var1, var5));
         }

         var14.setAccessible(true);

         try {
            var14.set(var1, var4);
         } catch (IllegalAccessException var13) {
            throw new SerializationException(var13, var6);
         }
      }
   }

   protected Deserializer_v5.CustomMethods getCustomMethods(Object var1, Class var2) throws SerializationException {
      Class var3 = var2 == null ? var1.getClass() : var2;
      Deserializer_v5.CustomMethods var4 = (Deserializer_v5.CustomMethods)customMethods.get(var3);
      if (var4 == null) {
         var4 = new Deserializer_v5.CustomMethods();
         byte var5 = 0;

         for (Method var9 : var3.getDeclaredMethods()) {
            for (Annotation var13 : var9.getAnnotations()) {
               Class var14 = var13.annotationType();
               if ((var5 & 1) == 0 && var14 == SerCustomRead.class) {
                  if (!this.isValidMethodCustomRead(var9)) {
                     throw new SerializationException("Invalid prototype for custom reader method: " + var9);
                  }

                  var4.mCustomRead = var9;
                  var5 |= 1;
                  break;
               }

               if ((var5 & 2) == 0 && var14 == SerCustomInit.class) {
                  if (!this.isValidMethodCustomInit(var9)) {
                     throw new SerializationException("Invalid prototype for custom initializer method: " + var9);
                  }

                  var4.mCustomInit = var9;
                  var5 |= 2;
                  break;
               }

               if ((var5 & 4) == 0 && var14 == SerCustomInitPostGraph.class) {
                  if (!this.isValidMethodCustomInit(var9)) {
                     throw new SerializationException("Invalid prototype for custom post-graph initializer method: " + var9);
                  }

                  var4.mCustomInitPostGraph = var9;
                  var5 |= 4;
                  break;
               }
            }

            if (var5 == 7) {
               break;
            }
         }

         customMethods.put(var3, var4);
      }

      return var4;
   }

   private boolean isValidMethodCustomRead(Method var1) {
      Class[] var2 = var1.getParameterTypes();
      if (var2.length == 1 && var2[0] == DeserializerHelper.class) {
         Class var3 = var1.getReturnType();
         return var3 != void.class ? false : (var1.getModifiers() & 2) != 0;
      } else {
         return false;
      }
   }

   private boolean isValidMethodCustomInit(Method var1) {
      Class[] var2 = var1.getParameterTypes();
      if (var2.length != 0) {
         return false;
      } else {
         Class var3 = var1.getReturnType();
         return var3 != void.class ? false : (var1.getModifiers() & 2) != 0;
      }
   }

   protected void resolveDeferredObjects() {
      if (!this.deferredObjects.isEmpty()) {
         IdentityHashSet var1 = new IdentityHashSet();

         for (IPreObject var3 : this.deferredObjects) {
            Object var4 = var3.getObject();
            if (var4 != null) {
               var1.add(var4);
            }
         }

         while (!this.deferredObjects.isEmpty()) {
            int var7 = 0;
            ArrayList var8 = new ArrayList();

            for (IPreObject var5 : this.deferredObjects) {
               if (var5.canBuild(this.objmap, var1)) {
                  Object var6 = var5.build(this.objmap);
                  var1.remove(var6);
                  var7++;
               } else {
                  var8.add(var5);
               }
            }

            this.deferredObjects = var8;
            if (var7 == 0) {
               RuntimeException var10 = new RuntimeException("deser deferred obj problem 0");
               if (Licensing.isDebugBuild()) {
                  throw new RuntimeException(var10);
               }

               JebCoreService.notifySilentExceptionToClient(var10);

               for (IPreObject var12 : this.deferredObjects) {
                  var12.build(this.objmap);
               }

               this.deferredObjects.clear();
               break;
            }
         }
      }
   }

   @Override
   public void setExpectedObjectCount(int var1) {
      this.expectedObjectCount = var1;
   }

   @Override
   public void addProgressCallback(IProgressCallback var1) {
      this.progressCallbacks.add(var1);
   }

   @Override
   public void removeProgressCallback(IProgressCallback var1) {
      this.progressCallbacks.remove(var1);
   }

   protected void notifyProgressCallbacks() {
      for (IProgressCallback var2 : this.progressCallbacks) {
         if (!var2.isInitialized()) {
            var2.setTotal(this.expectedObjectCount);
         }

         var2.setCurrent(this.getObjectCount());
      }
   }

   @Override
   public void addObjectCreatedHook(Class var1, IDeserializationEventHandler var2) {
      if (!var1.isInterface() && !var1.isArray() && !var1.isPrimitive()) {
         this.objectCreatedHookMap.put(var1, var2);
      } else {
         throw new IllegalArgumentException(
            Strings.ff("Illegal object type to be monitored: %s (must be a pure object: no interface, no array, no primitive)", var1.getSimpleName())
         );
      }
   }

   @Override
   public void removeObjectCreatedHook(Class var1, IDeserializationEventHandler var2) {
      this.objectCreatedHookMap.removeValue(var1, var2);
   }

   protected void notifyDeserializationEvent(int var1, Class var2, Object var3) {
      if (var1 == 1) {
         for (IDeserializationEventHandler var5 : this.objectCreatedHookMap.getSafe(var2)) {
            var5.notifyObjectCreated(var2, var3);
         }
      }
   }

   public void setStringPool(List var1) {
      this.stringpool = var1;
   }

   public List getStringPool() {
      return this.stringpool;
   }

   protected Object readInternal(Object var1, Class var2, int[] var3) throws IOException {
      boolean var4 = var2 == null;
      if (var4 && var1 != null) {
         throw new SerializationException("Object should not be provided, a leaf is being deserialized", var2);
      } else {
         Object var5 = var1;
         int var6 = this.in.readIntULEB128();
         int var7 = 0;
         String var8 = null;
         int var10 = 0;
         int var9;
         if (var4) {
            if (var6 == 1) {
               return null;
            }

            if ((var6 & 64) != 0) {
               int var32 = var6 & 63;
               switch (var32) {
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
                  case 11:
                     return this.in.readByte();
                  case 12:
                     return (byte)0;
                  case 13:
                     return (byte)1;
                  case 14:
                     return (byte)-1;
                  case 15:
                     return this.in.readShort();
                  case 16:
                     return (short)0;
                  case 17:
                     return (short)1;
                  case 18:
                     return Short.valueOf((short)-1);
                  case 19:
                     return this.in.readChar();
                  case 20:
                     return '\u0000';
                  case 21:
                     return this.in.readFloat();
                  case 22:
                     return this.in.readDouble();
                  default:
                     throw new SerializationException("Unknown special object index: " + var32);
               }
            }

            if ((var6 & 16) != 0) {
               var10 = this.in.readByte() & 255;
            }

            var7 = this.in.readIntULEB128();
            if ((var6 & 4) != 0) {
               Object var31 = this.objGet(var7);
               if (var31 == null) {
                  throw new SerializationException(Strings.ff("Expected an already-deserialized object for id: %d", var7), var2);
               }

               return var31;
            }

            if ((var6 & 8) != 0) {
               Object var30 = this.objGet(var7);
               if (var30 == null) {
                  this.requestedObjectIds.add(var7);
                  if (var3 != null) {
                     var3[0] = var7;
                  }
               }

               return var30;
            }

            this.requestedObjectIds.remove(var7);
            var9 = this.in.readIntULEB128() - 128;
            if ((var6 & 256) != 0) {
               int var11 = this.in.readIntULEB128();
               byte[] var12 = new byte[var11];
               this.in.readFully(var12);
               var8 = Strings.decodeUTF8(var12);
            } else if ((var6 & 512) != 0) {
               int var28 = this.in.readIntULEB128();
               Class var36 = this.objGet(var28).getClass();
               var8 = ReflectionHelper.reduceDimensions(var36).getName();
            }

            Class var29;
            try {
               if (var9 > 0) {
                  var29 = this.customTypeIdProvider.getType(var9);
               } else if (var9 < 0) {
                  var29 = this.nativeTypeIdProvider.getType(var9);
               } else {
                  var29 = this.loadClass(var8);
               }
            } catch (ClassNotFoundException var25) {
               throw new SerializationException(Strings.ff("Class not found for type id: %d", var9), var25, var2);
            }

            if (var2 != null && var2 != var29) {
               throw new SerializationException(Strings.ff("Unexpected class was found for type id %d: %s", var9, var29), var2);
            }

            var2 = var29;
            if (var29 == null) {
               throw new SerializationException(Strings.ff("Type was expected for id %d, got null", var9), var29);
            }
         } else {
            var9 = this.nativeTypeIdProvider.getId(var2);
            if (var9 == 0) {
               var9 = this.customTypeIdProvider.getId(var2);
            }
         }

         if ((var6 & 2) != 0) {
            int var35 = this.in.readIntULEB128();
            var5 = var2.getEnumConstants()[var35];
            this.registerObject(true, var7, var5);
            return var5;
         } else if ((var6 & 128) != 0) {
            int var34 = this.in.readIntULEB128();

            try {
               var5 = var2.getDeclaredMethod("valueOf", int.class).invoke(null, var34);
            } catch (ReflectiveOperationException var19) {
               throw new SerializationException(var19);
            }

            this.registerObject(true, var7, var5);
            return var5;
         } else {
            int var33 = (var6 & 32) != 0 ? this.in.readIntULEB128() : 0;
            if (var9 >= 0 && var10 == 0) {
               if (var4) {
                  Object var55 = this.constructorMap.get(var2);
                  if (var55 == null) {
                     var55 = this.mootObject;

                     try {
                        Constructor var72 = var2.getDeclaredConstructor();
                        if (var72.getAnnotation(SerConstructor.class) != null) {
                           var55 = var72;
                        }
                     } catch (NoSuchMethodException var23) {
                     } catch (Exception var24) {
                        throw new SerializationException(var24, var2);
                     }

                     this.constructorMap.put(var2, var55);
                  }

                  try {
                     if (var55 instanceof Constructor) {
                        var5 = ReflectionHelper.newInstance2((Constructor)var55);
                     } else {
                        var5 = this.objenesis.getInstantiatorOf(var2).newInstance();
                     }
                  } catch (Exception var22) {
                     throw new SerializationException(var22, var2);
                  }

                  this.registerObject(var4, var7, var5);
               }

               Class var56 = var2.getSuperclass();
               if (var56 != null) {
                  this.read(var5, var56, null);
               }

               Deserializer_v5.CustomMethods var73 = this.getCustomMethods(var5, var2);
               if (var73.mCustomRead != null) {
                  try {
                     ReflectionHelper.invoke2(var73.mCustomRead, var5, new DeserializerHelper(this, var5, var2, var33));
                  } catch (Exception var21) {
                     throw new SerializationException("Custom read failed", var21, var2);
                  }
               } else {
                  this.restoreFields(var5, var2);
               }

               if (var73.mCustomInit != null) {
                  try {
                     ReflectionHelper.invoke2(var73.mCustomInit, var5);
                  } catch (Exception var20) {
                     throw new SerializationException("Initializer failed", var20, var2);
                  }
               }

               this.notifyDeserializationEvent(1, var2, var5);
            } else if (var10 > 0) {
               int[] var54 = new int[var10];
               int var71 = this.in.readIntULEB128();
               var54[0] = var71;
               Object var83 = Array.newInstance(var2, var54);
               this.registerObject(var4, var7, var83);
               PreArray var85 = new PreArray(var83, var71);
               int[] var87 = new int[1];

               for (int var89 = 0; var89 < var71; var89++) {
                  Object var91 = this.read(null, null, var87);
                  if (var87[0] == 0) {
                     var85.recordObj(var91);
                  } else {
                     var85.recordId(var87[0]);
                     var87[0] = 0;
                  }
               }

               this.deferredObjects.add(var85);
               var5 = var83;
            } else if (var2 == Object.class) {
               if (var1 == null) {
                  var1 = new Object();
               }

               var5 = var1;
            } else if (var2 == Class.class) {
               String var53 = null;
               int var70 = this.in.readInt();
               if (var70 == 0) {
                  var53 = this.in.readUTF();
               }

               var5 = this.retrieveClassFromIdOrName(var70, var53);
            } else if (var2 == Boolean.class) {
               var5 = this.in.readByte() == 0 ? Boolean.FALSE : Boolean.TRUE;
            } else if (var2 == boolean[].class) {
               int var52 = this.in.readIntULEB128();
               boolean[] var69 = new boolean[var52];

               for (int var82 = 0; var82 < var52; var82++) {
                  var69[var82] = this.in.readByte() != 0;
               }

               var5 = var69;
            } else if (var2 == Byte.class) {
               var5 = this.in.readByte();
            } else if (var2 == byte[].class) {
               int var51 = this.in.readIntULEB128();
               byte[] var68 = new byte[var51];

               for (int var81 = 0; var81 < var51; var81++) {
                  var68[var81] = this.in.readByte();
               }

               var5 = var68;
            } else if (var2 == Character.class) {
               var5 = this.in.readChar();
            } else if (var2 == char[].class) {
               int var50 = this.in.readIntULEB128();
               char[] var67 = new char[var50];

               for (int var80 = 0; var80 < var50; var80++) {
                  var67[var80] = this.in.readChar();
               }

               var5 = var67;
            } else if (var2 == Short.class) {
               var5 = this.in.readShort();
            } else if (var2 == short[].class) {
               int var49 = this.in.readIntULEB128();
               short[] var66 = new short[var49];

               for (int var79 = 0; var79 < var49; var79++) {
                  var66[var79] = this.in.readShort();
               }

               var5 = var66;
            } else if (var2 == Integer.class) {
               var5 = this.in.readIntULEB128();
            } else if (var2 == int[].class) {
               int var48 = this.in.readIntULEB128();
               int[] var65 = new int[var48];

               for (int var78 = 0; var78 < var48; var78++) {
                  var65[var78] = this.in.readIntULEB128();
               }

               var5 = var65;
            } else if (var2 == Long.class) {
               var5 = this.in.readLongULEB128();
            } else if (var2 == long[].class) {
               int var47 = this.in.readIntULEB128();
               long[] var64 = new long[var47];

               for (int var77 = 0; var77 < var47; var77++) {
                  var64[var77] = this.in.readLongULEB128();
               }

               var5 = var64;
            } else if (var2 == Float.class) {
               var5 = this.in.readFloat();
            } else if (var2 == float[].class) {
               int var46 = this.in.readIntULEB128();
               float[] var63 = new float[var46];

               for (int var76 = 0; var76 < var46; var76++) {
                  var63[var76] = this.in.readFloat();
               }

               var5 = var63;
            } else if (var2 == Double.class) {
               var5 = this.in.readDouble();
            } else if (var2 == double[].class) {
               int var45 = this.in.readIntULEB128();
               double[] var62 = new double[var45];

               for (int var75 = 0; var75 < var45; var75++) {
                  var62[var75] = this.in.readDouble();
               }

               var5 = var62;
            } else if (var2 != String.class && var2 != StringBuilder.class) {
               if (var2 == BigInteger.class) {
                  int var44 = this.in.readIntULEB128();
                  byte[] var61 = new byte[var44];
                  this.in.readFully(var61);
                  var5 = new BigInteger(var61);
               } else if (var2 == BigDecimal.class) {
                  int var43 = this.in.readIntULEB128();
                  byte[] var60 = new byte[var43];
                  this.in.readFully(var60);
                  var5 = new BigDecimal(Strings.decodeUTF8(var60));
               } else if (var2 == AtomicBoolean.class) {
                  var5 = new AtomicBoolean(this.in.readByte() != 0);
               } else if (var2 == AtomicInteger.class) {
                  var5 = new AtomicInteger(this.in.readIntULEB128());
               } else if (var2 == AtomicLong.class) {
                  var5 = new AtomicLong(this.in.readLongULEB128());
               } else if (var2 != SoftReference.class && var2 != WeakReference.class) {
                  if (Collection.class.isAssignableFrom(var2)) {
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
                        int var58 = this.in.readIntULEB128();
                        boolean var41;
                        if (var58 < 0) {
                           var58 = -var58;
                           var41 = false;
                        } else {
                           var41 = true;
                        }

                        Object var14;
                        if (var4) {
                           if (var2 == ArrayList.class) {
                              var14 = new ArrayList(var58);
                           } else if (var2 == LinkedList.class) {
                              var14 = new LinkedList();
                           } else if (var2 == ArrayDeque.class) {
                              var14 = new ArrayDeque(var58);
                           } else if (var2 == HashSet.class) {
                              var14 = new HashSet(var58);
                           } else if (var2 == TreeSet.class) {
                              var14 = new TreeSet();
                           } else if (var2 == LinkedHashSet.class) {
                              var14 = new LinkedHashSet();
                           } else if (var2 == CopyOnWriteArrayList.class) {
                              var14 = new CopyOnWriteArrayList();
                           } else if (var2 == CopyOnWriteArraySet.class) {
                              var14 = new CopyOnWriteArraySet();
                           } else if (var2 == ConcurrentSkipListSet.class) {
                              var14 = new ConcurrentSkipListSet();
                           } else {
                              if (var2 != ConcurrentLinkedQueue.class) {
                                 throw new SerializationException("Unsupported collection type", var2);
                              }

                              var14 = new ConcurrentLinkedQueue();
                           }
                        } else {
                           var14 = (Collection)var1;
                        }

                        this.registerObject(var4, var7, var14);
                        if (!var41) {
                           throw new RuntimeException();
                        }

                        PreCollection var15 = new PreCollection((Collection)var14, var58);
                        int[] var16 = new int[1];

                        for (int var17 = 0; var17 < var58; var17++) {
                           Object var18 = this.read(null, null, var16);
                           if (var16[0] == 0) {
                              var15.recordObj(var18);
                           } else {
                              var15.recordId(var16[0]);
                              var16[0] = 0;
                           }
                        }

                        this.deferredObjects.add(var15);
                        var5 = var14;
                     } else if (var2 == AbstractList.class) {
                        if (var4) {
                           throw new RuntimeException();
                        }

                        Collection var40 = (Collection)var1;
                        var5 = var40;
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

                     int var42 = this.in.readIntULEB128();
                     Object var59;
                     if (var4) {
                        if (var2 == HashMap.class) {
                           var59 = new HashMap(var42);
                        } else if (var2 == TreeMap.class) {
                           var59 = new TreeMap();
                        } else if (var2 == LinkedHashMap.class) {
                           var59 = new LinkedHashMap(var42);
                        } else if (var2 == IdentityHashMap.class) {
                           var59 = new IdentityHashMap(var42);
                        } else if (var2 == ConcurrentHashMap.class) {
                           var59 = new ConcurrentHashMap(var42);
                        } else {
                           if (var2 != ConcurrentSkipListMap.class) {
                              throw new SerializationException("Unsupported map type", var2);
                           }

                           var59 = new ConcurrentSkipListMap();
                        }
                     } else {
                        var59 = (Map)var1;
                     }

                     this.registerObject(var4, var7, var59);
                     PreMap var74 = new PreMap((Map)var59, var42);
                     int[] var84 = new int[1];

                     for (int var86 = 0; var86 < var42; var86++) {
                        Object var88 = this.read(null, null, var84);
                        if (var84[0] == 0) {
                           var74.recordObj(var88);
                        } else {
                           var74.recordId(var84[0]);
                           var84[0] = 0;
                        }

                        Object var90 = this.read(null, null, var84);
                        if (var84[0] == 0) {
                           var74.recordObj(var90);
                        } else {
                           var74.recordId(var84[0]);
                           var84[0] = 0;
                        }
                     }

                     this.deferredObjects.add(var74);
                     var5 = var59;
                  }
               } else {
                  if (!var4) {
                     throw new RuntimeException("Unexpected non-leaf SoftReference object");
                  }

                  int[] var39 = new int[1];
                  Object var57 = this.read(null, null, var39);
                  if (var39[0] == 0) {
                     var5 = var2 == SoftReference.class ? new SoftReference(var57) : new WeakReference(var57);
                  } else {
                     var5 = new Deserializer_v5.TemporaryReference(var2, var39[0]);
                     var39[0] = 0;
                  }
               }
            } else {
               if (this.stringpool != null) {
                  int var37 = this.in.readIntULEB128();
                  var5 = this.stringpool.get(var37);
               } else {
                  int var38 = this.in.readIntULEB128();
                  byte[] var13 = new byte[var38];
                  this.in.readFully(var13);
                  var5 = Strings.decodeUTF8(var13);
               }

               if (var2 == StringBuilder.class) {
                  var5 = new StringBuilder((String)var5);
               }
            }

            this.registerObject(var4, var7, var5);
            return var5;
         }
      }
   }

   @Override
   public void restoreFields(Object var1, Class var2) throws IOException {
      int[] var3 = new int[1];

      while (true) {
         int var4 = this.in.readIntULEB128();
         if (var4 == 0) {
            return;
         }

         Object var5 = this.read(null, null, var3);
         this.restoreFieldValue(var1, var2, var4, var5, var3[0]);
         var3[0] = 0;
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

   static class CustomMethods {
      Method mCustomRead;
      Method mCustomInit;
      Method mCustomInitPostGraph;
   }

   static class DeferredFieldValue {
      Field f;
      Object o;
      int fieldObjectId;

      DeferredFieldValue(Field var1, Object var2, int var3) {
         this.f = var1;
         this.o = var2;
         this.fieldObjectId = var3;
      }

      @Override
      public String toString() {
         return this.f + " (field id: " + this.fieldObjectId + ")";
      }
   }

   class ObjlistAsMap implements Map {
      @Override
      public int size() {
         return Deserializer_v5.this.objcnt;
      }

      @Override
      public boolean isEmpty() {
         return Deserializer_v5.this.objcnt == 0;
      }

      @Override
      public boolean containsKey(Object var1) {
         throw new RuntimeException("Do not use");
      }

      @Override
      public boolean containsValue(Object var1) {
         throw new RuntimeException("Do not use");
      }

      @Override
      public Object get(Object var1) {
         int var2 = (Integer)var1;
         return var2 >= 0 && var2 < Deserializer_v5.this.objlist.size() ? Deserializer_v5.this.objlist.get((Integer)var1) : null;
      }

      public Object put(Integer var1, Object var2) {
         throw new RuntimeException("Do not use");
      }

      @Override
      public Object remove(Object var1) {
         throw new RuntimeException("Do not use");
      }

      @Override
      public void putAll(Map var1) {
         throw new RuntimeException("Do not use");
      }

      @Override
      public void clear() {
         throw new RuntimeException("Do not use");
      }

      @Override
      public Set keySet() {
         throw new RuntimeException("Do not use");
      }

      @Override
      public Collection values() {
         throw new RuntimeException("Do not use");
      }

      @Override
      public Set entrySet() {
         throw new RuntimeException("Do not use");
      }
   }

   static class TemporaryReference implements IPreObject {
      Class refClazz;
      int refObjectId;

      TemporaryReference(Class var1, int var2) {
         this.refClazz = var1;
         this.refObjectId = var2;
      }

      @Override
      public boolean canBuild(Map var1, IdentityHashSet var2) {
         return true;
      }

      @Override
      public Object build(Map var1) {
         Object var2 = var1.get(this.refObjectId);
         if (this.refClazz == SoftReference.class) {
            return new SoftReference(var2);
         } else if (this.refClazz == WeakReference.class) {
            return new WeakReference(var2);
         } else {
            throw new RuntimeException();
         }
      }

      @Override
      public Object getObject() {
         return null;
      }
   }
}
