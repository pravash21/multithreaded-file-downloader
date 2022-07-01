package com.pravash;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import static com.pravash.Status.RUNNING;
import static com.pravash.Status.COMPLETED;
import static com.pravash.Status.QUEUED;
import static com.pravash.Status.UNKNOWN;
class Downloads {

    int totalDownloads = 0;
    Semaphore maxDownload = new Semaphore(5);
    static ArrayList<URL> download;

    void addDownload(String url) throws InterruptedException {
        try {
            URL urlOb = new URL(url);
            synchronized (this) {
                download.add(urlOb);
                while(totalDownloads >= 5) {
                    download.get(download.size() - 1).setStatus(QUEUED);
                }
                maxDownload.acquire();
                download.get(download.size() - 1).setStatus(RUNNING);
                totalDownloads++;

            }
            maxDownload.release();
            synchronized (this) {
                download.get(download.size() - 1).setStatus(COMPLETED);
                totalDownloads--;

            }

        }
        catch (Exception e) {
            download.get(download.size() - 1).setStatus(UNKNOWN);
        }


    }

    String getStatus(String url1) {
        for(int i = 0; i < download.size(); i++) {
            if (download.get(i).getUrl().equals(url1))
                return download.get(i).getStatus().toString();
        }
        return UNKNOWN.toString();
    }

    void printDownload() {
        for(int i = 0; i < download.size(); i++) {
            System.out.println(download.get(i).getUrl() + " " + download.get(i).getStatus().toString());
        }
    }

    void deleteDownload(String url1) {
        for(int i = 0; i < download.size(); i++) {
            if(download.get(i).getUrl().equals(url1))
                download.remove(download.get(i));
        }
    }




        public static void main(String args[]) throws InterruptedException {
        download = new ArrayList<>();
        Downloads ob = new Downloads();
           Thread url1 = new Thread(new Runnable() {
               @Override
               public void run() {
                   try {
                       ob.addDownload("a.com/file1.mp4");
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
               }
               });
            Thread url2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ob.addDownload("b.com/file1.mp4");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Thread url3 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ob.addDownload("c.com/file1.mp4");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Thread url4 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ob.addDownload("d.com/file1.mp4");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Thread url5 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ob.addDownload("e.com/file1.mp4");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Thread url6 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ob.addDownload("f.com/file1.mp4");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Thread url7 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ob.addDownload("g.com/file1.mp4");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Thread url8 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ob.addDownload("h.com/file1.mp4");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Thread url9 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ob.addDownload("i.com/file1.mp4");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Thread url10 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ob.addDownload("j.com/file1.mp4");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Thread url11 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ob.addDownload("k.com/file1.mp4");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            url1.start();
            url2.start();
            url3.start();
            url4.start();
            url5.start();
            url6.start();
            url7.start();
            url8.start();
            url9.start();
            url10.start();
            url11.start();
            url1.join();
            url2.join();
            url3.join();
            url4.join();
            url5.join();

            url6.join();
            url7.join();
            url8.join();
            url9.join();
            url10.join();
            url11.join();
            System.out.println("a.com/file1.mp4" + " " + ob.getStatus("a.com/file1.mp4"));
            System.out.println("b.com/file1.mp4" + " " + ob.getStatus("b.com/file1.mp4"));
            System.out.println("c.com/file1.mp4" + " " + ob.getStatus("c.com/file1.mp4"));
            System.out.println("d.com/file1.mp4" + " " + ob.getStatus("d.com/file1.mp4"));
            System.out.println("f.com/file1.mp4" + " " + ob.getStatus("f.com/file1.mp4"));
            System.out.println("e.com/file1.mp4" + " " + ob.getStatus("e.com/file1.mp4"));
            System.out.println("g.com/file1.mp4" + " " + ob.getStatus("g.com/file1.mp4"));
            //ob.printDownload();
            

    }
}


public enum Status {
    QUEUED,
    RUNNING,
    COMPLETED,
    UNKNOWN;

}

public class URL {
    String url;
    Status status;
    URL(String url) {
        this.url = url;
    }

    String getUrl() {
        return url;
    }

    Status getStatus() {
        return status;
    }

    void setStatus(Status status) {
        this.status = status;
    }
}
