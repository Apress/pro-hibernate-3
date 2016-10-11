package com.hibernatebook.chapter10.events;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.event.AutoFlushEvent;
import org.hibernate.event.DeleteEvent;
import org.hibernate.event.DirtyCheckEvent;
import org.hibernate.event.EvictEvent;
import org.hibernate.event.FlushEntityEvent;
import org.hibernate.event.FlushEvent;
import org.hibernate.event.InitializeCollectionEvent;
import org.hibernate.event.LoadEvent;
import org.hibernate.event.LockEvent;
import org.hibernate.event.MergeEvent;
import org.hibernate.event.PersistEvent;
import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostLoadEvent;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PreDeleteEvent;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreLoadEvent;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.RefreshEvent;
import org.hibernate.event.ReplicateEvent;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.def.DefaultAutoFlushEventListener;
import org.hibernate.event.def.DefaultDeleteEventListener;
import org.hibernate.event.def.DefaultDirtyCheckEventListener;
import org.hibernate.event.def.DefaultEvictEventListener;
import org.hibernate.event.def.DefaultFlushEntityEventListener;
import org.hibernate.event.def.DefaultFlushEventListener;
import org.hibernate.event.def.DefaultInitializeCollectionEventListener;
import org.hibernate.event.def.DefaultLoadEventListener;
import org.hibernate.event.def.DefaultLockEventListener;
import org.hibernate.event.def.DefaultMergeEventListener;
import org.hibernate.event.def.DefaultPersistEventListener;
import org.hibernate.event.def.DefaultPostDeleteEventListener;
import org.hibernate.event.def.DefaultPostInsertEventListener;
import org.hibernate.event.def.DefaultPostLoadEventListener;
import org.hibernate.event.def.DefaultPostUpdateEventListener;
import org.hibernate.event.def.DefaultPreDeleteEventListener;
import org.hibernate.event.def.DefaultPreInsertEventListener;
import org.hibernate.event.def.DefaultPreLoadEventListener;
import org.hibernate.event.def.DefaultPreUpdateEventListener;
import org.hibernate.event.def.DefaultRefreshEventListener;
import org.hibernate.event.def.DefaultReplicateEventListener;
import org.hibernate.event.def.DefaultSaveOrUpdateEventListener;

/**
 * This rather busy little class is supposed to call all of the Session methods
 * so that we can figure out which of the associated EventListeners will get
 * called as a result, in order to create the table mapping methods to events in
 * Chapter 10.
 * 
 * We can look at the H3 imlementation directly to see this, but this is simpler
 * and more authoritative.
 * 
 * This won't appear in the text of the book, but we'll probably leave it in the
 * sample code.
 */
public class EventExerciser {
   public static void main(String[] args) throws Exception {
      // Exercises each of the Hibernate Session methods
      // in turn so that we can capture the sequence of
      // events that is generated as a result.
      Configuration config = new Configuration();

      // Apply this event listener (programmatically)
      config.setListener("auto-flush",new NoisyAutoFlushEventListener());
      config.setListener("delete",new NoisyDeleteEventListener());
      config.setListener("dirty-check",new NoisyDirtyCheckEventListener());
      config.setListener("evict",new NoisyEvictEventListener());
      config.setListener("flush-entity",new NoisyFlushEntityEventListener());
      config.setListener("flush",new NoisyFlushEventListener());
      config.setListener("load-collection",new NoisyInitializeCollectionEventListener());
      config.setListener("load",new NoisyLoadEventListener());
      config.setListener("lock",new NoisyLockEventListener());
      config.setListener("merge",new NoisyMergeEventListener());
      config.setListener("persist",new NoisyPersistEventListener());
      config.setListener("post-delete",new NoisyPostDeleteEventListener());
      config.setListener("post-insert",new NoisyPostInsertEventListener());
      config.setListener("post-load",new NoisyPostLoadEventListener());
      config.setListener("post-update",new NoisyPostUpdateEventListener());
      config.setListener("pre-delete",new NoisyPreDeleteEventListener());
      config.setListener("pre-insert",new NoisyPreInsertEventListener());
      config.setListener("pre-load",new NoisyPreLoadEventListener());
      config.setListener("pre-update",new NoisyPreUpdateEventListener());
      config.setListener("refresh",new NoisyRefreshEventListener());
      config.setListener("replicate",new NoisyReplicateEventListener());
      config.setListener("save-update",new NoisySaveOrUpdateEventListener());


      SessionFactory factory = config.configure().buildSessionFactory();
      Session session = factory.openSession();

      System.out.println("clear()");
      session.clear();

      System.out.println("flush()");
      session.flush();

      System.out.println("getFlushMode()");
      FlushMode flushMode = session.getFlushMode();

      System.out.println("setFlushMode(FlushMode)");
      session.setFlushMode(flushMode);

      System.out.println("getCacheMode()");
      CacheMode cacheMode = session.getCacheMode();

      System.out.println("setCacheMode(CacheMode)");
      session.setCacheMode(cacheMode);

      System.out.println("getEntityMode()");
      EntityMode entityMode = session.getEntityMode();

      System.out.println("getSessionFactory()");
      factory = session.getSessionFactory();

      System.out.println("isOpen()");
      session.isOpen();

      System.out.println("isConnected()");
      session.isConnected();

      System.out.println("isDirty()");
      session.isDirty();

      System.out.println("beginTransaction()");
      Transaction tx = session.beginTransaction();

      TestEntity t1 = new TestEntity("tom");
      TestEntity t2 = new TestEntity("dick");
      TestEntity t3 = new TestEntity("harry");
      TestEntity t4 = new TestEntity("susan");
      TestEntity t5 = new TestEntity("clare");
      
      //////////////////////////////////////////////

      System.out.println("save(Object)");
      session.save(t1);
      session.flush();
      
      System.out.println("persist(Serializable)");
      session.persist(t2);
      session.flush();

      System.out.println("persist(String,Serializable)");
      session.persist("com.hibernatebook.chapter10.events.TestEntity", t3);
      session.flush();
      
      System.out.println("getIdentifier(Serializable)");
      Serializable id = session.getIdentifier(t1);
      session.flush();

      //Not invoked...
      //Filter filter = session.enableFilter("FILTER NAME");
      //Query   session.createFilter(lazyLoadedCollection,"FILTER QUERY");
      //filter = session.getEnabledFilter("FILTER NAME");
      //session.disableFilter("FILTER NAME");
      
      System.out.println("evict(Object)");
      session.evict(t1);
      System.out.println("update(Object)");
      session.update(t1);

      System.out.println("evict(Object)");
      session.evict(t1);
      System.out.println("update(Object,Serializable)");
      session.update(t1, id);

      System.out.println("evict(Object)");
      session.evict(t1);
      System.out.println("update(String,Object)");
      session.update("com.hibernatebook.chapter10.events.TestEntity", t1);

      System.out.println("evict(Object)");
      session.evict(t1);
      System.out.println("update(String,Object,Serializable)");
      session.update("com.hibernatebook.chapter10.events.TestEntity", t1, id); 
      
      System.out.println("evict(Object)");
      session.evict(t1);      
      System.out.println("refresh(Object)");
      session.refresh(t1);

      System.out.println("evict(Object)");
      session.evict(t1);
      System.out.println("refresh(Object,LockMode)");
      session.refresh(t1,LockMode.UPGRADE_NOWAIT);
      
      System.out.println("evict(Object)");
      session.evict(t1);
      System.out.println("load(Object,Serializable)");
      session.load(new TestEntity(),id);

      System.out.println("evict(Object)");
      session.evict(t1);
      System.out.println("load(Class,Serializable)");
      t1 = (TestEntity)session.load(TestEntity.class,id);

      System.out.println("evict(Object)");
      session.evict(t1);
      System.out.println("load(Class,Serializable,LockMode)");
      t1 = (TestEntity)session.load(TestEntity.class,id,LockMode.UPGRADE_NOWAIT);

      System.out.println("evict(Object)");
      session.evict(t1);
      System.out.println("load(String,Serializable)");      
      t1 = (TestEntity)session.load("com.hibernatebook.chapter10.events.TestEntity",id);

      System.out.println("evict(Object)");
      session.evict(t1);
      System.out.println("load(String,Serializable,LockMode)");      
      t1 = (TestEntity)session.load("com.hibernatebook.chapter10.events.TestEntity",id,LockMode.UPGRADE_NOWAIT);

      System.out.println("lock(Object,LockMode)");      
      session.lock(t1,LockMode.NONE);      
      System.out.println("lock(String,Object,LockMode)");      
      session.lock("com.hibernatebook.chapter10.events.TestEntity",t2,LockMode.NONE);

      System.out.println("contains(Object)");
      session.contains(t1);
      
      System.out.println("getEntityName(Object)");
      System.out.println("Name = "  + session.getEntityName(t1));      
      
      System.out.println("evict(Object)");
      session.evict(t1);

      // Deletions and re-additions.
      session.delete(t3);
      session.flush();

      // Saves...
      System.out.println("save(Object,Serializable)");
      session.save(t3, new Integer(100));
      
      System.out.println("save(String,Object,Serializable)");
      session.save("com.hibernatebook.chapter10.events.TestEntity", t4, new Integer(101));
      
      System.out.println("save(String,Object)");
      session.save("com.hibernatebook.chapter10.events.TestEntity",t5);
      
      System.out.println("saveOrUpdate(Object)");
      session.saveOrUpdate(t3);
      
      System.out.println("saveOrUpdate(String,Object)");
      session.saveOrUpdate("com.hibernatebook.chapter10.events.TestEntity",t4);

      t1.setValue("REPLICATE1");
      System.out.println("replicate(Object,ReplicationMode)");
      session.replicate(t1, ReplicationMode.LATEST_VERSION);

      t1.setValue("REPLICATE2");
      System.out.println("replicate(String,Object,ReplicationMode)");
      session.replicate("com.hibernatebook.chapter10.events.TestEntity", t1, ReplicationMode.LATEST_VERSION);
      
      session.get(TestEntity.class, id);
      session.get(TestEntity.class, id, LockMode.NONE);
      session.get("com.hibernatebook.chapter10.events.TestEntity", id);
      session.get("com.hibernatebook.chapter10.events.TestEntity", id, LockMode.NONE);

      session.merge(t1);
      session.merge("com.hibernatebook.chapter10.events.TestEntity", t1);
      
      System.out.println("getNamedQuery(String)");
      Query q1 =  session.getNamedQuery("eventTestQuery");
      System.out.println("[Query.uniqueResult()]");      
      Object o1 = q1.uniqueResult();
      session.evict(o1);

      System.out.println("createQuery(String)");
      Query q2 = session.createQuery("FROM TestEntity where cvalue = :value");
      q2.setString("value","dick");
      System.out.println("[Query.uniqueResult()]");      
      Object o2 = q2.uniqueResult();
      session.evict(o2);

      System.out.println("createCriteria(Class)");
      Criteria c1 = session.createCriteria(TestEntity.class);
      System.out.println("[Criteria.add]");
      c1.add(Restrictions.eq("Value","harry"));
      System.out.println("[Criteria.list]");
      System.out.println(c1.list().size());
           
      Criteria c2 = session.createCriteria(TestEntity.class,"ALIAS");
      System.out.println("[Criteria.add]");
      c2.add(Restrictions.eq("Value","harry"));
      System.out.println("[Criteria.list]");
      System.out.println(c2.list().size());

      Criteria c3 = session.createCriteria(TestEntity.class.getName());
      System.out.println("[Criteria.add]");
      c3.add(Restrictions.eq("Value","harry"));
      System.out.println("[Criteria.list]");
      System.out.println(c3.list().size());

      Criteria c4 = session.createCriteria(TestEntity.class.getName(),"ALIAS");
      System.out.println("[Criteria.add]");
      c4.add(Restrictions.eq("Value","harry"));
      System.out.println("[Criteria.list]");
      System.out.println(c4.list().size());
      
      System.out.println("createSQLQuery(String)");
      SQLQuery sq1 = session.createSQLQuery("select {ent.*} from TestEntity ent");
      System.out.println("[SQLQuery.addEntity]");
      sq1.addEntity("ent",TestEntity.class);
      System.out.println("[SQLQuery.list]");
      List list = sq1.list();
      System.out.println("[Process SQLQuery List]");
      Iterator i = list.iterator();
      while(i.hasNext()) {
         TestEntity te = (TestEntity)i.next();
         System.out.println(te.getId() + " := " + te.getValue());
         session.evict(te);
      }
      
      //////////////////////////////////////////////
      
      System.out.println("delete()");
      session.delete(t1);
      System.out.println("delete()");
      session.delete(t2);
      System.out.println("delete()");
      session.delete(t3);
      System.out.println("delete()");
      session.delete(t4);
      System.out.println("delete()");
      session.delete(t5);
      System.out.println("flush()");
      session.flush();
      
      System.out.println("[tx.commit()]");
      tx.commit();

      //Not carried out.
      //Session  emSession = session.openSession(EntityMode.MAP);
      //Map map = (Map)emSession.get(className,id);
      
      System.out.println("cancelQuery()");
      session.cancelQuery();
      session.flush();
      
      System.out.println("connection()");
      Connection connection = session.connection();
      System.out.println(connection);

      System.out.println("disconnect()");
      session.disconnect();

      System.out.println("reconnect(Connection)");
      session.reconnect(connection);
      
      System.out.println("disconnect()");      
      session.disconnect();

      System.out.println("reconnect()");
      session.reconnect();
      System.out.println(session.connection());

      System.out.println("close()");
      session.close();
      
   }

   private static class NoisyAutoFlushEventListener extends
         DefaultAutoFlushEventListener {
      public boolean onAutoFlush(AutoFlushEvent event)
            throws HibernateException {
         System.out.println("AutoFlushEventListener.onAutoFlush(AutoFlushEvent)");
         return super.onAutoFlush(event);
      }
   }

   private static class NoisyDeleteEventListener extends
         DefaultDeleteEventListener {
      public void onDelete(DeleteEvent event) throws HibernateException {
         System.out.println("DeleteEventListener.onDelete(DeleteEvent)");
         super.onDelete(event);
      }
   }

   private static class NoisyDirtyCheckEventListener extends
         DefaultDirtyCheckEventListener {
      public boolean onDirtyCheck(DirtyCheckEvent event)
            throws HibernateException {
         System.out.println("DirtyCheckEventListener.onDirtyCheck(DirtyCheckEvent)");
         return super.onDirtyCheck(event);
      }
   }

   private static class NoisyEvictEventListener extends
         DefaultEvictEventListener {
      public void onEvict(EvictEvent event) throws HibernateException {
         System.out.println("EvictEventListener.onEvict(EvictEvent)");
         super.onEvict(event);
      }
   }

   private static class NoisyFlushEntityEventListener extends
         DefaultFlushEntityEventListener {
      public void onFlushEntity(FlushEntityEvent event)
            throws HibernateException {
         System.out.println("FlushEntityEventListener.onFlushEntity(FlushEntityEvent)");
         super.onFlushEntity(event);
      }
   }

   private static class NoisyFlushEventListener extends
         DefaultFlushEventListener {
      public void onFlush(FlushEvent event) throws HibernateException {
         System.out.println("FlushEventListener.onFlush(FlushEvent)");
         super.onFlush(event);
      }
   }

   private static class NoisyInitializeCollectionEventListener extends
         DefaultInitializeCollectionEventListener {
      public void onInitializeCollection(InitializeCollectionEvent event)
            throws HibernateException {
         System.out.println("InitializeCollectionEventListener.onInitializeCollection(InitializeCollectionEvent)");
         super.onInitializeCollection(event);
      }
   }

   private static class NoisyLoadEventListener extends
         DefaultLoadEventListener {
      public Object onLoad(LoadEvent event, LoadType type)
            throws HibernateException {
         System.out.println("LoadEventListener.onLoad(LoadEvent,LoadType)");
         return super.onLoad(event, type);
      }
   }

   private static class NoisyLockEventListener extends
         DefaultLockEventListener {
      public void onLock(LockEvent event) throws HibernateException {
         System.out.println("LockEventListener.onLock(LockEvent)");
         super.onLock(event);
      }
   }

   private static class NoisyMergeEventListener extends
         DefaultMergeEventListener {
      public Object onMerge(MergeEvent event, Map copiedAlready)
            throws HibernateException {
         System.out.println("MergeEventListener.onMerge(MergeEvent,Map)");
         return super.onMerge(event, copiedAlready);
      }

      public Object onMerge(MergeEvent event) throws HibernateException {
         System.out.println("MergeEventListener.onMerge(MergeEvent)");
         return super.onMerge(event);
      }
   }

   private static class NoisyPersistEventListener extends
         DefaultPersistEventListener {
      public void onPersist(PersistEvent event, Map createdAlready)
            throws HibernateException {
         System.out.println("PersistEventListener.onPersist(PersistEvent,Map)");
         super.onPersist(event, createdAlready);
      }

      public void onPersist(PersistEvent event)
            throws HibernateException {
         System.out.println("PersistEventListener.onPersist(PersistEvent)");
         super.onPersist(event);
      }
   }

   private static class NoisyPostDeleteEventListener extends
         DefaultPostDeleteEventListener {
      public void onPostDelete(PostDeleteEvent event) {
         System.out.println("PostDeleteEventListener.onPostDelete(PostDeleteEvent)");
         super.onPostDelete(event);
      }
   }

   private static class NoisyPostInsertEventListener extends
         DefaultPostInsertEventListener {
      public void onPostInsert(PostInsertEvent event) {
         System.out.println("PostInsertEventListener.onPostInsert(PostInsertEvent)");
         super.onPostInsert(event);
      }
   }

   private static class NoisyPostLoadEventListener extends
         DefaultPostLoadEventListener {
      public void onPostLoad(PostLoadEvent event) {
         System.out.println("PostLoadEventListener.onPostLoad(onPostLoad)");
         super.onPostLoad(event);
      }
   }

   private static class NoisyPostUpdateEventListener extends
         DefaultPostUpdateEventListener {
      public void onPostUpdate(PostUpdateEvent event) {
         System.out.println("PostUpdateEventListener.onPostUpdate(PostUpdateEvent)");
         super.onPostUpdate(event);
      }
   }

   private static class NoisyPreDeleteEventListener extends
         DefaultPreDeleteEventListener {
      public boolean onPreDelete(PreDeleteEvent event) {
         System.out.println("PreDeleteEventListener.onPreDelete(PreDeleteEvent)");
         return super.onPreDelete(event);
      }
   }

   private static class NoisyPreInsertEventListener extends
         DefaultPreInsertEventListener {
      public boolean onPreInsert(PreInsertEvent event) {
         System.out.println("PreInsertEventListener.onPreInsert(PreInsertEvent)");
         return super.onPreInsert(event);
      }
   }

   private static class NoisyPreLoadEventListener extends
         DefaultPreLoadEventListener {
      public void onPreLoad(PreLoadEvent event) {
         System.out.println("PreLoadEventListener.onPreLoad(PreLoadEvent)");
         super.onPreLoad(event);
      }
   }

   private static class NoisyPreUpdateEventListener extends
         DefaultPreUpdateEventListener {
      public boolean onPreUpdate(PreUpdateEvent event) {
         System.out.println("PreUpdateEventListener.onPreUpdate(PreUpdateEvent)");
         return super.onPreUpdate(event);
      }
   }

   private static class NoisyRefreshEventListener extends
         DefaultRefreshEventListener {
      public void onRefresh(RefreshEvent event)
            throws HibernateException {
         System.out.println("RefreshEventListener.onRefresh(RefreshEvent)");
         super.onRefresh(event);
      }
   }

   private static class NoisyReplicateEventListener extends
         DefaultReplicateEventListener {
      public void onReplicate(ReplicateEvent event)
            throws HibernateException {
         System.out.println("ReplicateEventListener.onReplicate(ReplicateEvent)");
         super.onReplicate(event);
      }
   }

   private static class NoisySaveOrUpdateEventListener extends
         DefaultSaveOrUpdateEventListener {
      public Serializable onSaveOrUpdate(SaveOrUpdateEvent event)
            throws HibernateException {
         System.out.println("SaveOrUpdateEventListener.onSaveOrUpdate(SaveOrUpdateEvent)");
         return super.onSaveOrUpdate(event);
      }
   }

}