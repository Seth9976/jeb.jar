package com.pnfsoftware.jeb.util.serialization;

import com.pnfsoftware.jeb.util.base.IProgressCallback;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.reflect.ReflectionHelper;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInit;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomRead;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import org.objenesis.ObjenesisStd;

public abstract class AbstractInternalDeserializer implements IInternalDeserializer {
   private static final ILogger logger = GlobalLog.getLogger(AbstractInternalDeserializer.class);
   protected LEDataInputStream in;
   protected ITypeIdProvider nativeTypeIdProvider;
   protected ITypeIdProvider customTypeIdProvider;
   protected List classloaders;
   protected ObjenesisStd objenesis;
   protected boolean cancelled;
   protected Map constructorMap = new HashMap();
   protected Object mootObject = new Object();
   protected Map objmap = new TreeMap();
   protected Set objIdPostgraphDone = new HashSet();
   protected List deferredObjects = new ArrayList();
   private static final int thresholdDepthWarning = 150;
   private int depth;
   private int maxDepthReached;
   private static Map fieldCache = new HashMap();
   protected static Map customMethods = new HashMap();
   protected int expectedObjectCount;
   protected List progressCallbacks = new ArrayList();
   protected MultiMap objectCreatedHookMap = new MultiMap();

   protected AbstractInternalDeserializer(ITypeIdProvider var1, List var2, LEDataInputStream var3) {
      this.in = var3;
      this.nativeTypeIdProvider = NativeTypeIdProvider.getInstance();
      this.customTypeIdProvider = var1;
      this.classloaders = var2;
      this.objenesis = new ObjenesisStd();
   }

   @Override
   public int getObjectCount() {
      return this.objmap.size();
   }

   @Override
   public Collection getObjects() {
      return this.objmap.values();
   }

   @Override
   public InputStream getStream() {
      return new AbstractInternalDeserializer$1(this);
   }

   @Override
   public Object deserializeInternal() throws IOException {
      Object var1;
      try {
         var1 = this.read(null, null);
      } finally {
         this.resolveDeferredObjects();
      }

      for (Entry var3 : this.objmap.entrySet()) {
         Integer var4 = (Integer)var3.getKey();
         if (this.objIdPostgraphDone.add(var4)) {
            Object var5 = var3.getValue();
            Class var6 = var5.getClass();

            while (true) {
               AbstractInternalDeserializer.CustomMethods var7 = this.getCustomMethods(var5, var6);
               if (var7.mCustomInitPostGraph != null) {
                  try {
                     ReflectionHelper.invoke2(var7.mCustomInitPostGraph, var5);
                  } catch (Exception var11) {
                     throw new SerializationException("Post-graph initializer failed", var11, null);
                  }
               }

               var6 = var6.getSuperclass();
               if (var6 == null) {
                  break;
               }
            }
         }
      }

      if (this.cancelled) {
         throw new SerializationException("The deserialization process was cancelled");
      } else {
         this.notifyProgressCallbacks();
         return var1;
      }
   }

   public void close() throws IOException {
      this.in.close();
      this.in = null;
   }

   @Override
   public Object read() throws IOException {
      return this.read(null, null);
   }

   public Object read(Object var1, Class var2) throws IOException {
      this.depth++;

      Object var3;
      try {
         if (this.depth > this.maxDepthReached) {
            this.maxDepthReached = this.depth;
            if (this.depth > 150) {
               logger.debug("Deserialization depth: %d: read() o=%s c=%s", this.depth, var1 == null ? "null" : var1.getClass(), var2);
            }
         }

         if (this.cancelled) {
            return null;
         }

         if (!Thread.interrupted()) {
            return this.readInternal(var1, var2);
         }

         this.cancelled = true;
         var3 = null;
      } finally {
         this.depth--;
         if (this.depth == 0 && this.depth > 150) {
            logger.debug("Maximum depth reached during deserialization: %d", this.maxDepthReached);
         }
      }

      return var3;
   }

   protected abstract Object readInternal(Object var1, Class var2) throws IOException;

   protected void registerObject(boolean var1, int var2, Object var3) {
      if (var1) {
         Object var4 = this.objmap.put(var2, var3);
         if (var4 != var3 && !this.objmap.isEmpty() && this.objmap.size() % 1000000 == 0) {
            logger.debug("Deserializing... status: %d unique objects", this.objmap.size());
            this.notifyProgressCallbacks();
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

   protected void restoreFieldValue(Object var1, Class var2, int var3, Object var4) throws SerializationException {
      Class var5 = var2 == null ? var1.getClass() : var2;
      Object var6 = (Map)fieldCache.get(var5);
      if (var6 == null) {
         var6 = new HashMap();
         fieldCache.put(var5, var6);

         for (Field var10 : var5.getDeclaredFields()) {
            SerId var11 = (SerId)var10.getAnnotation(SerId.class);
            if (var11 != null) {
               var6.put(var11.value(), var10);
            }
         }
      }

      Field var13 = (Field)var6.get(var3);
      if (var13 != null) {
         boolean var14 = var13.canAccess(var1);
         if (!var14) {
            var13.setAccessible(true);
         }

         try {
            var13.set(var1, var4);
         } catch (IllegalAccessException var12) {
            throw new SerializationException(var12, var5);
         }

         if (!var14) {
            var13.setAccessible(false);
         }
      }
   }

   protected AbstractInternalDeserializer.CustomMethods getCustomMethods(Object var1, Class var2) throws SerializationException {
      Class var3 = var2 == null ? var1.getClass() : var2;
      AbstractInternalDeserializer.CustomMethods var4 = (AbstractInternalDeserializer.CustomMethods)customMethods.get(var3);
      if (var4 == null) {
         var4 = new AbstractInternalDeserializer.CustomMethods();
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
      for (IPreObject var2 : this.deferredObjects) {
         var2.build(this.objmap);
      }

      this.deferredObjects.clear();
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

   protected void logObjectMap() {
      int var1 = 1;

      for (int var3 : this.objmap.keySet()) {
         if (var3 != var1) {
            logger.trace("! GAP !");
         }

         Object var4 = this.objmap.get(var3);
         if (var4 == null) {
            logger.trace("%d -> null !!", var3);
         } else {
            logger.trace("%d -> %s (%Xh)", var3, var4.getClass().getName(), var4.hashCode());
         }

         var1 = var3 + 1;
      }
   }

   static class CustomMethods {
      Method mCustomRead;
      Method mCustomInit;
      Method mCustomInitPostGraph;
   }
}
