package doanlehyvong.demo.controller;

import doanlehyvong.demo.model.Book;
import doanlehyvong.demo.services.BookService;
import doanlehyvong.demo.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {

        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/add";
    }


    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model)
        {
            if (bindingResult.hasErrors()) {
                model.addAttribute("categories", categoryService.getAllCategories());
                return "book/add";
            }
            bookService.addBook(book);
            return "redirect:/books";
        }


    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            model.addAttribute("book", book);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        } else {
            return "not-found";
        }
    }
    //    @PostMapping("/edit")
//    public String editBook(@ModelAttribute("book") Book updatedBook)
//    {
//        bookService.updateBook(updatedBook);
//        return "redirect:/books";
//    }
    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("book") Book updatedBook, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }

        Book bookToUpdate = bookService.getBookById(updatedBook.getId());
        if (bookToUpdate != null) {
            // Thực hiện cập nhật thông tin của đầu sách
            bookToUpdate.setTitle(updatedBook.getTitle());
            bookToUpdate.setAuthor(updatedBook.getAuthor());
            bookToUpdate.setPrice(updatedBook.getPrice());
            bookToUpdate.setCategory(updatedBook.getCategory());

            bookService.updateBook(bookToUpdate);
        }

        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id)
    {
        Book book = bookService.getBookById(id);
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
