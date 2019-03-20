package bookstore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BookAccess{

    public List<Book> getAllBooks(){
        List<Book> bookList = null;
        try{
            File file = new File("Books.dat");
            if(!file.exists()){
                Book book1 = new Book(12340, "George Orwell", "1984", "Post-Apocalyptic Fiction");
                Book book2 = new Book(8000, "J. R. R. Tolkien", "The Lord of the Rings", "Fantasy");
                Book book3 = new Book(9000, "Harper Lee", "To Kill a Mockingbird", "Southern Gothic Fiction");
                Book book4 = new Book(555, "Jane Austen", "Pride and Prejudice", "Comedy");
                bookList = new ArrayList<Book>();
                bookList.add(book1);
                bookList.add(book2);
                bookList.add(book3);
                bookList.add(book4);
                saveBookList(bookList);
            }
            else{
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                bookList = (List<Book>) ois.readObject();
                ois.close();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return bookList;
    }

    private void saveBookList(List<Book> bookList){
        try{
            File file = new File("Books.dat");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(bookList);
            oos.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public Book getBook(int id){
        List<Book> bookList = getAllBooks();
        for(Book book : bookList){
            if(book.getId() == id) return book;
        }
        return null;
    }

    public int addBook(Book nBook){
        List<Book> bookList = getAllBooks();
        generateId(nBook);
        for(Book book : bookList){
            if(book.equals(nBook) || book.getId() == nBook.getId()) return 0;
        }
        bookList.add(nBook);
        saveBookList(bookList);
        return 1;
    }

    public int updateBook(int oId, Book uBook){
        List<Book> bookList = getAllBooks();
        generateId(nBook);
        for(Book book : bookList){
            if(book.getId() == oId){
                int index = bookList.indexOf(book);
                bookList.set(index, uBook);
                saveBookList(bookList);
                return 1;
            }
        }
        return 0;
    }

    public int deleteBook(int id){
        List<Book> bookList = getAllBooks();
        for(Book book : bookList){
            if(book.getId() == id){
                bookList.remove(book);
                saveBookList(bookList);
                return 1;
            }
        }
        return 0;
    }
        
    public void generateId(Book book){
        if(book.getId() == null){
            int bookId = 1;
            while(1){
                if(getBook(bookId) == null){
                    nBook.setId(bookId);
                    break;
                }
                else bookId++;
            }
        }
        return;
    }

}
