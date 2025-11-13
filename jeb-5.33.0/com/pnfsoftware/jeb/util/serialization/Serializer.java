package com.pnfsoftware.jeb.util.serialization;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.DynamicEnum;
import com.pnfsoftware.jeb.util.base.IProgressCallback;
import com.pnfsoftware.jeb.util.collect.IntList;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.LEDataOutputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.reflect.ReflectionHelper;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomWrite;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jeb.util.serialization.annotations.SerVersion;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.AbstractList;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Deque;
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
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Serializer implements IInternalSerializer {
   private static final ILogger logger = GlobalLog.getLogger(Serializer.class);
   private static final int VERSION = 5;
   private boolean simumode = false;
   private final LEDataOutputStream out0;
   private LEDataOutputStream out;
   private ITypeIdProvider nativeTypeIdProvider;
   private ITypeIdProvider customTypeIdProvider;
   private IdentityHashMap objmap = new IdentityHashMap();
   private int leafStack = 0;
   private Deque objQueue = new ArrayDeque();
   private IntList dumpOffsets;
   private int writtenObjectCount;
   private LinkedHashMap stringpool;
   private Map t2omap = new HashMap();
   private int currentObjectId = 1;
   private int rootCount;
   private boolean cancelled;
   private long lastWrittenSize;
   private static Map fieldCache = new HashMap();
   private static Map fieldIdCache = new HashMap();
   private Set impTransientFields = new HashSet();
   private static Map customWriters = new HashMap();
   private static Map versionFields = new HashMap();
   private int expectedObjectCount;
   private List progressCallbacks = new ArrayList();
   private Map customSerializationClassFlags = new HashMap();

   public Serializer(ITypeIdProvider customTypeIdProvider, OutputStream out, boolean b) {
      this.out0 = new LEDataOutputStream(new BufferedOutputStream(out));
      this.out = this.out0;
      (this.dumpOffsets = new IntList(256, 1048576)).add(-1);
      this.stringpool = b ? new LinkedHashMap() : null;
      this.nativeTypeIdProvider = NativeTypeIdProvider.getInstance();
      this.customTypeIdProvider = customTypeIdProvider;
   }

   public void setup(boolean b) {
      if (this.cancelled) {
         throw new IllegalStateException("The object was cancelled");
      } else {
         if (b) {
            if (this.currentObjectId != 1) {
               throw new IllegalStateException("Simulation mode cannot be enabled when previous (real) serializations were performed by this object");
            }

            this.simumode = true;
            this.out = new LEDataOutputStreamFake();
         } else {
            this.simumode = false;
            this.out = this.out0;
         }

         this.init();
      }
   }

   private void init() {
      this.objmap.clear();
      this.leafStack = 0;
      this.objQueue.clear();
      this.dumpOffsets.clear();
      this.dumpOffsets.add(-1);
      this.writtenObjectCount = 0;
      this.t2omap.clear();
      this.currentObjectId = 1;
      this.rootCount = 0;
   }

   void writeHeader() throws IOException {
      int n = 0;
      if (this.stringpool != null) {
         n |= 1;
      }

      this.out.write("PNF-ORPD".getBytes("UTF-8"));
      this.out.writeByte(5);
      this.out.writeByte(n);
      this.out.writeByte(0);
      this.out.writeByte(0);
      this.out.writeInt(0);
   }

   @Override
   public OutputStream getStream() {
      return new OutputStream() {
         @Override
         public void write(int b) throws IOException {
            Serializer.this.out.write(b);
         }

         @Override
         public void write(byte[] b) throws IOException {
            Serializer.this.out.write(b);
         }

         @Override
         public void write(byte[] array, int n, int n2) throws IOException {
            Serializer.this.out.write(array, n, n2);
         }
      };
   }

   public int getRootCount() {
      return this.rootCount;
   }

   public int getObjectCount() {
      return this.objmap.size();
   }

   public int getWrittenObjectCount() {
      return this.writtenObjectCount;
   }

   public long getLastWrittenSize() {
      return this.lastWrittenSize;
   }

   public List getStringPool() {
      if (this.stringpool == null) {
         throw new IllegalStateException("This serializer was not configured to generate a string pool");
      } else {
         return new ArrayList(this.stringpool.keySet());
      }
   }

   public void serialize(Object o) throws IOException {
      if (this.rootCount == 0) {
         this.writeHeader();
      }

      this.out.flush();
      long n = this.out.size();
      Assert.a(this.leafStack == 0);
      Assert.a(this.objQueue.isEmpty());
      this.objQueue.push(new Serializer.E(o));

      int n2;
      for (n2 = 0; !this.objQueue.isEmpty(); n2++) {
         Serializer.E e = (Serializer.E)this.objQueue.pop();
         this.write(e.o, null, e.fieldIdInContainer, e.containerSerTypeId);
      }

      if (!this.simumode) {
         this.rootCount += n2;
      }

      if (this.cancelled) {
         throw new SerializationException(Strings.ff("The serialization of object \"%s\" was cancelled", o));
      } else {
         Assert.a(this.leafStack == 0);
         Assert.a(this.objQueue.isEmpty());
         this.out.flush();
         this.lastWrittenSize = this.out.size() - n;
         this.notifyProgressCallbacks();
      }
   }

   public void close() throws IOException {
      Assert.a(this.getObjectCount() == this.currentObjectId - 1);
      this.out.flush();
      this.out.close();
      this.out = null;
   }

   @Override
   public void write(Object o) throws IOException {
      this.write(o, null, null, null);
   }

   private void write(Object o, Class clazz, Integer n, Integer n2) throws IOException {
      if (!this.cancelled) {
         if (Thread.interrupted()) {
            this.cancelled = true;
         } else {
            boolean b = clazz == null;
            if (o == null) {
               if (!b) {
                  throw new SerializationException("A null object must be a leaf object", clazz);
               } else {
                  this.out.writeIntULEB128(1);
               }
            } else {
               if (b) {
                  this.leafStack++;
               }

               try {
                  this.writeI(o, clazz, n, n2);
               } finally {
                  if (b) {
                     this.leafStack--;
                  }
               }
            }
         }
      }
   }

   private void writeI(Object o, Class clazz, Integer n, Integer n2) throws IOException {
      boolean b = clazz == null;
      Class clazz2 = b ? o.getClass() : clazz;
      int n3 = 0;
      if (clazz2.isEnum()) {
         n3 |= 2;
         if (!b) {
            throw new SerializationException("Enum must be a leaf object", clazz2);
         }
      }

      if (DynamicEnum.class.isInstance(o)) {
         n3 |= 128;
      }

      if (b) {
         if (clazz2 == Boolean.class) {
            this.out.writeIntULEB128(64 | ((Boolean)o ? 1 : 0));
            return;
         }

         if (clazz2 == Byte.class) {
            byte byteValue = (Byte)o;
            if (byteValue == 0) {
               this.out.writeIntULEB128(76);
            } else if (byteValue == 1) {
               this.out.writeIntULEB128(77);
            } else if (byteValue == -1) {
               this.out.writeIntULEB128(78);
            } else {
               this.out.writeIntULEB128(75);
               this.out.writeByte(byteValue);
            }

            return;
         }

         if (clazz2 == Short.class) {
            short shortValue = (Short)o;
            if (shortValue == 0) {
               this.out.writeIntULEB128(80);
            } else if (shortValue == 1) {
               this.out.writeIntULEB128(81);
            } else if (shortValue == -1) {
               this.out.writeIntULEB128(82);
            } else {
               this.out.writeIntULEB128(79);
               this.out.writeShort(shortValue);
            }

            return;
         }

         if (clazz2 == Character.class) {
            char charValue = (Character)o;
            if (charValue == 0) {
               this.out.writeIntULEB128(84);
            } else {
               this.out.writeIntULEB128(83);
               this.out.writeChar(charValue);
            }

            return;
         }

         if (clazz2 == Integer.class) {
            int intValue = (Integer)o;
            if (intValue == 0) {
               this.out.writeIntULEB128(69);
            } else if (intValue == 1) {
               this.out.writeIntULEB128(70);
            } else if (intValue == -1) {
               this.out.writeIntULEB128(71);
            } else {
               this.out.writeIntULEB128(66);
               this.out.writeIntULEB128(intValue);
            }

            return;
         }

         if (clazz2 == Long.class) {
            long longValue = (Long)o;
            if (longValue == 0L) {
               this.out.writeIntULEB128(72);
            } else if (longValue == 1L) {
               this.out.writeIntULEB128(73);
            } else if (longValue == -1L) {
               this.out.writeIntULEB128(74);
            } else {
               this.out.writeIntULEB128(67);
               this.out.writeLongULEB128(longValue);
            }

            return;
         }

         if (clazz2 == String.class && ((String)o).length() == 0) {
            this.out.writeIntULEB128(68);
            return;
         }

         if (clazz2 == Float.class) {
            float floatValue = (Float)o;
            this.out.writeIntULEB128(85);
            this.out.writeFloat(floatValue);
            return;
         }

         if (clazz2 == Double.class) {
            double doubleValue = (Double)o;
            this.out.writeIntULEB128(86);
            this.out.writeDouble(doubleValue);
            return;
         }

         Integer value = (Integer)this.objmap.get(o);
         if (value != null && value < this.dumpOffsets.size() && this.dumpOffsets.get(value) >= 0) {
            this.out.writeIntULEB128(n3 | 4);
            this.out.writeIntULEB128(value);
            return;
         }

         if (this.leafStack >= 2) {
            if (value == null) {
               value = this.currentObjectId;
               this.currentObjectId++;
               this.dumpOffsets.add(-1);
               this.objmap.put(o, value);
               this.objQueue.push(new Serializer.E(o, n, n2));
            }

            this.out.writeIntULEB128(n3 | 8);
            this.out.writeIntULEB128(value);
            return;
         }
      }

      int n4 = 0;

      while (clazz2.isArray()) {
         Class componentType = clazz2.getComponentType();
         if (componentType.isPrimitive()) {
            break;
         }

         n4++;
         clazz2 = componentType;
      }

      String name = null;
      int intValue2 = -1;
      int i = this.nativeTypeIdProvider.getId(clazz2);
      if (i == 0) {
         i = this.customTypeIdProvider.getId(clazz2);
         if (i == 0 && b) {
            name = clazz2.getName();
            Integer n5 = (Integer)this.t2omap.get(name);
            if (n5 != null) {
               n3 |= 512;
               intValue2 = n5;
            } else {
               n3 |= 256;
               if (name.startsWith("com.pnfsoftware.jeb") && !name.startsWith("com.pnfsoftware.jeb.test.")) {
                  throw new SerializationException("JEB classes must have a type ID to be serializable", clazz2);
               }
            }
         }
      }

      boolean b2 = i >= 0 && n4 == 0;
      boolean b3 = (n3 & 2) != 0;
      boolean b4 = (n3 & 128) != 0;
      int n6 = b2 && !b3 && !b4 ? this.getVersionField(o, clazz2) : 0;
      if (n6 != 0) {
         n3 |= 32;
      }

      if (!b) {
         this.out.writeIntULEB128(n3);
      } else {
         int size = this.out.size();
         if (n4 == 0) {
            this.out.writeIntULEB128(n3);
         } else {
            n3 |= 16;
            this.out.writeIntULEB128(n3);
            this.out.writeByte(n4);
         }

         Integer value2 = (Integer)this.objmap.get(o);
         if (value2 == null) {
            value2 = this.currentObjectId;
            this.currentObjectId++;
            this.dumpOffsets.add(-1);
            this.objmap.put(o, value2);
         }

         this.dumpOffsets.set(value2, size);
         this.writtenObjectCount++;
         this.out.writeIntULEB128(value2);
         if (i == 0 && !this.t2omap.containsKey(name)) {
            this.t2omap.put(name, value2);
         }

         if (this.writtenObjectCount % 200000 == 0) {
            logger.debug("Serializing... status: %d objects", this.writtenObjectCount);
            this.notifyProgressCallbacks();
         }

         this.out.writeIntULEB128(i + 128);
         if ((n3 & 256) != 0) {
            byte[] encodeUTF8 = Strings.encodeUTF8(name);
            this.out.writeIntULEB128(encodeUTF8.length);
            this.out.write(encodeUTF8);
         } else if ((n3 & 512) != 0) {
            this.out.writeIntULEB128(intValue2);
         }
      }

      if ((n3 & 2) != 0) {
         this.out.writeIntULEB128(((Enum)o).ordinal());
      } else if ((n3 & 128) != 0) {
         this.out.writeIntULEB128(((DynamicEnum)o).id());
      } else {
         if ((n3 & 32) != 0) {
            this.out.writeIntULEB128(n6);
         }

         if (b2) {
            Class superclass = clazz2.getSuperclass();
            if (superclass != null) {
               this.write(o, superclass, n, n2);
            }

            if (!this.attemptCustomWrite(o, clazz2)) {
               this.writeFields(o, clazz2, i);
            }
         } else if (n4 > 0) {
            int length = Array.getLength(o);
            this.out.writeIntULEB128(length);

            for (int j = 0; j < length; j++) {
               this.write(Array.get(o, j), null, n, n2);
            }
         } else {
            this.writeNativeObject(o, clazz2, n, n2);
         }
      }
   }

   @Override
   public void writeFields(Object o, Class clazz) throws IOException {
      this.writeFields(o, clazz, null);
   }

   public void writeFields(Object o, Class clazz, Integer n) throws IOException {
      List list = (List)fieldCache.get(clazz);
      if (list == null) {
         list = new ArrayList();
         fieldCache.put(clazz, list);
         HashSet set = new HashSet();

         for (Field field : clazz.getDeclaredFields()) {
            if ((field.getModifiers() & 8) == 0) {
               SerId serId = (SerId)field.getAnnotation(SerId.class);
               if (serId == null) {
                  if (field.getAnnotation(SerTransient.class) == null && !this.impTransientFields.contains(field)) {
                     this.impTransientFields.add(field);
                     logger.debug(Strings.ff("Field %s is implicitly transient", field));
                  }
               } else {
                  int value = serId.value();
                  if (value == 0) {
                     logger.warn(Strings.ff("Invalid field serialization id (0) for field: %s", field));
                  } else if (!serId.deprecated()) {
                     list.add(field);
                     fieldIdCache.put(field, value);
                     if (!set.add(value)) {
                        throw new SerializationException("Duplicate field id: " + value, clazz);
                     }
                  }
               }
            }
         }
      }

      for (Object field2 : list) {
         this.writeField(o, (Field)field2, (Integer)fieldIdCache.get(field2), n);
      }

      this.out.writeIntULEB128(0);
   }

   private void writeField(Object o, Field field, int i, Integer n) throws IOException {
      boolean canAccess = field.canAccess(o);
      if (!canAccess) {
         field.setAccessible(true);
      }

      Object value;
      try {
         value = field.get(o);
      } catch (IllegalAccessException var8) {
         throw new SerializationException(var8);
      }

      this.out.writeIntULEB128(i);
      this.write(value, null, i, n);
      if (!canAccess) {
         field.setAccessible(false);
      }
   }

   private boolean attemptCustomWrite(Object o, Class clazz) throws SerializationException {
      Class clazz2 = clazz == null ? o.getClass() : clazz;

      try {
         Method method;
         if (customWriters.containsKey(clazz2)) {
            method = (Method)customWriters.get(clazz2);
         } else {
            method = null;

            for (Method method2 : clazz2.getDeclaredMethods()) {
               if (method2.getAnnotation(SerCustomWrite.class) != null) {
                  if (!this.isValidSerializerHelper(method2)) {
                     throw new SerializationException("Invalid prototype for serialization helper method: " + method2, clazz2);
                  }

                  method = method2;
                  break;
               }
            }

            customWriters.put(clazz2, method);
         }

         if (method == null) {
            return false;
         } else {
            Integer n = (Integer)this.customSerializationClassFlags.get(clazz2);
            ReflectionHelper.invoke2(method, o, new SerializerHelper(this, o, clazz2, n == null ? 0 : n));
            return true;
         }
      } catch (Exception var9) {
         throw new SerializationException("Custom write failed", var9, clazz2);
      }
   }

   private boolean isValidSerializerHelper(Method method) {
      Class[] parameterTypes = method.getParameterTypes();
      return parameterTypes.length == 1
         && parameterTypes[0] == SerializerHelper.class
         && method.getReturnType() == void.class
         && (method.getModifiers() & 2) != 0;
   }

   private int getVersionField(Object o, Class clazz) throws SerializationException {
      try {
         Class clazz2 = clazz == null ? o.getClass() : clazz;
         Integer n = (Integer)versionFields.get(clazz2);
         if (n == null) {
            SerVersion serVersion = (SerVersion)clazz2.getAnnotation(SerVersion.class);
            if (serVersion != null) {
               n = serVersion.value();
            } else {
               n = 0;
            }

            versionFields.put(clazz2, n);
         }

         return n;
      } catch (SecurityException var6) {
         throw new SerializationException(var6);
      }
   }

   private void writeNativeObject(Object o, Class clazz, Integer n, Integer n2) throws IOException {
      if (clazz != Object.class) {
         if (clazz == Class.class) {
            String name = null;
            int n3 = this.nativeTypeIdProvider.getId((Class)o);
            if (n3 == 0) {
               n3 = this.customTypeIdProvider.getId((Class)o);
               if (n3 == 0) {
                  name = ((Class)o).getName();
               }
            }

            this.out.writeInt(n3);
            if (name != null) {
               this.out.writeUTF(name);
            }
         } else if (clazz == Boolean.class) {
            this.out.writeByte((Boolean)o ? 1 : 0);
         } else if (clazz == boolean[].class) {
            boolean[] array = (boolean[])o;
            this.out.writeIntULEB128(array.length);
            boolean[] array2 = array;
            int length = array.length;

            for (int i = 0; i < length; i++) {
               this.out.writeByte(array2[i] ? 1 : 0);
            }
         } else if (clazz == Byte.class) {
            this.out.writeByte((Byte)o);
         } else if (clazz == byte[].class) {
            byte[] b = (byte[])o;
            this.out.writeIntULEB128(b.length);
            this.out.write(b);
         } else if (clazz == Short.class) {
            this.out.writeShort((Short)o);
         } else if (clazz == short[].class) {
            short[] array3 = (short[])o;
            this.out.writeIntULEB128(array3.length);
            short[] array4 = array3;
            int length2 = array3.length;

            for (int j = 0; j < length2; j++) {
               this.out.writeShort(array4[j]);
            }
         } else if (clazz == Character.class) {
            this.out.writeChar((Character)o);
         } else if (clazz == char[].class) {
            char[] array5 = (char[])o;
            this.out.writeIntULEB128(array5.length);
            char[] array6 = array5;
            int length3 = array5.length;

            for (int k = 0; k < length3; k++) {
               this.out.writeChar(array6[k]);
            }
         } else if (clazz == Integer.class) {
            this.out.writeIntULEB128((Integer)o);
         } else if (clazz == int[].class) {
            int[] array7 = (int[])o;
            this.out.writeIntULEB128(array7.length);
            int[] array8 = array7;
            int length4 = array7.length;

            for (int l = 0; l < length4; l++) {
               this.out.writeIntULEB128(array8[l]);
            }
         } else if (clazz == Long.class) {
            this.out.writeLongULEB128((Long)o);
         } else if (clazz == long[].class) {
            long[] array9 = (long[])o;
            this.out.writeIntULEB128(array9.length);
            long[] array10 = array9;
            int length5 = array9.length;

            for (int n4 = 0; n4 < length5; n4++) {
               this.out.writeLongULEB128(array10[n4]);
            }
         } else if (clazz == Float.class) {
            this.out.writeFloat((Float)o);
         } else if (clazz == float[].class) {
            float[] array11 = (float[])o;
            this.out.writeIntULEB128(array11.length);
            float[] array12 = array11;
            int length6 = array11.length;

            for (int n5 = 0; n5 < length6; n5++) {
               this.out.writeFloat(array12[n5]);
            }
         } else if (clazz == Double.class) {
            this.out.writeDouble((Double)o);
         } else if (clazz == double[].class) {
            double[] array13 = (double[])o;
            this.out.writeIntULEB128(array13.length);
            double[] array14 = array13;
            int length7 = array13.length;

            for (int n6 = 0; n6 < length7; n6++) {
               this.out.writeDouble(array14[n6]);
            }
         } else if (clazz != String.class && clazz != StringBuilder.class) {
            if (clazz == BigInteger.class) {
               byte[] byteArray = ((BigInteger)o).toByteArray();
               this.out.writeIntULEB128(byteArray.length);
               this.out.write(byteArray);
            } else if (clazz == BigDecimal.class) {
               byte[] encodeUTF9 = Strings.encodeUTF8(((BigDecimal)o).toString());
               this.out.writeIntULEB128(encodeUTF9.length);
               this.out.write(encodeUTF9);
            } else if (clazz == AtomicBoolean.class) {
               this.out.writeByte(((AtomicBoolean)o).get() ? 1 : 0);
            } else if (clazz == AtomicInteger.class) {
               this.out.writeIntULEB128(((AtomicInteger)o).get());
            } else if (clazz == AtomicLong.class) {
               this.out.writeLongULEB128(((AtomicLong)o).get());
            } else if (clazz == SoftReference.class) {
               this.write(((SoftReference)o).get(), null, n, n2);
            } else if (clazz == WeakReference.class) {
               this.write(((WeakReference)o).get(), null, n, n2);
            } else if (Collection.class.isAssignableFrom(clazz)) {
               if (clazz == ArrayList.class
                  || clazz == LinkedList.class
                  || clazz == ArrayDeque.class
                  || clazz == HashSet.class
                  || clazz == TreeSet.class
                  || clazz == LinkedHashSet.class
                  || clazz == CopyOnWriteArrayList.class
                  || clazz == CopyOnWriteArraySet.class
                  || clazz == ConcurrentSkipListSet.class
                  || clazz == ConcurrentLinkedQueue.class) {
                  Collection collection = (Collection)o;
                  this.out.writeIntULEB128(collection.size());

                  for (Object next : collection) {
                     try {
                        ;
                     } catch (ConcurrentModificationException var12) {
                        if (n2 != null && n != null) {
                           var12.addSuppressed(new RuntimeException(Strings.ff("CME when serializing Collection field %d in ser.type %d", n, n2)));
                        }

                        throw var12;
                     }

                     this.write(next, null, n, n2);
                  }
               } else if (clazz == AbstractList.class) {
               }
            } else {
               if (!Map.class.isAssignableFrom(clazz)
                  || clazz != HashMap.class
                     && clazz != TreeMap.class
                     && clazz != LinkedHashMap.class
                     && clazz != IdentityHashMap.class
                     && clazz != ConcurrentHashMap.class
                     && clazz != ConcurrentSkipListMap.class) {
                  throw new SerializationException("Native object cannot be serialized", clazz);
               }

               Map map = (Map)o;
               this.out.writeIntULEB128(map.size());

               Set entrySet;
               try {
                  entrySet = map.entrySet();
               } catch (ConcurrentModificationException var11) {
                  if (n2 != null && n != null) {
                     var11.addSuppressed(new RuntimeException(Strings.ff("CME when serializing Map field %d in ser.type %d", n, n2)));
                  }

                  throw var11;
               }

               for (Object entry : entrySet) {
                  Object key = ((Entry)entry).getKey();
                  Object value2 = ((Entry)entry).getValue();
                  this.write(key, null, n, n2);
                  this.write(value2, null, n, n2);
               }
            }
         } else {
            String string;
            if (clazz == StringBuilder.class) {
               string = o.toString();
            } else {
               string = (String)o;
            }

            if (this.stringpool != null) {
               Integer value = (Integer)this.stringpool.get(string);
               if (value == null) {
                  value = this.stringpool.size();
                  this.stringpool.put(string, value);
               }

               this.out.writeIntULEB128(value);
            } else {
               byte[] encodeUTF8 = Strings.encodeUTF8(string);
               this.out.writeIntULEB128(encodeUTF8.length);
               this.out.write(encodeUTF8);
            }
         }
      }
   }

   static boolean isBoxedPrimitiveType(Class clazz) {
      return clazz == Boolean.class
         || clazz == Byte.class
         || clazz == Short.class
         || clazz == Character.class
         || clazz == Integer.class
         || clazz == Long.class
         || clazz == Float.class
         || clazz == Double.class;
   }

   public void setExpectedObjectCount(int expectedObjectCount) {
      this.expectedObjectCount = expectedObjectCount;
   }

   public void addProgressCallback(IProgressCallback progressCallback) {
      this.progressCallbacks.add(progressCallback);
   }

   public void removeProgressCallback(IProgressCallback progressCallback) {
      this.progressCallbacks.remove(progressCallback);
   }

   private void notifyProgressCallbacks() {
      for (IProgressCallback progressCallback : this.progressCallbacks) {
         if (this.expectedObjectCount != 0 && !progressCallback.isInitialized()) {
            progressCallback.setTotal(this.expectedObjectCount);
         }

         progressCallback.setCurrent(this.getWrittenObjectCount());
      }
   }

   public void setCustomSerializationClassFlags(Class clazz, int i) {
      this.customSerializationClassFlags.put(clazz, i);
   }

   static class E {
      Object o;
      Integer fieldIdInContainer;
      Integer containerSerTypeId;

      E(Object o) {
         this.o = o;
      }

      E(Object o, Integer fieldIdInContainer, Integer containerSerTypeId) {
         this.o = o;
         this.fieldIdInContainer = fieldIdInContainer;
         this.containerSerTypeId = containerSerTypeId;
      }
   }
}
