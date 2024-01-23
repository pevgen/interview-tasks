package interview.tasks.changes;

import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Сервис используется для консистентного переключения состояния из нескольких потоков;
 * при этом, мы хотим иметь возможность получить информацию
 * - сколько раз сервис был переключен,
 * - когда он был переключен последний раз
 * - прочитать строковый коментарий
 * <p/>
 * Проблема
 * тест работает, но на проде под нагрузкой иногда наблюдаем неконсистентность state и stateComment;
 * <p/>
 * Задача
 * Предположить в чём может быть дело и как можно было бы исправить ситуацию
 */
public class ChangeService {

    private LocalDateTime updateTime = LocalDateTime.now();
    private int state = 0;
    private String stateComment = "initialized";

    private final Lock writeLock = new ReentrantReadWriteLock().writeLock();

    public void update() {
        writeLock.lock();
        try {
            updateTime = LocalDateTime.now();
            state++;
            stateComment = String.format("updated: [%s] times", state);
        } finally {
            writeLock.unlock();
        }
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public int getState() {
        return state;
    }

    public String getStateComment() {
        return stateComment;
    }
}
